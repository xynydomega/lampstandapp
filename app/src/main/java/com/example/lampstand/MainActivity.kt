package com.example.lampstand

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.lampstand.auth.CreateAccountScreen
import com.example.lampstand.auth.DashboardSignedOutScreen
import com.example.lampstand.auth.LoadingScreen
import com.example.lampstand.auth.LoginScreen
import com.example.lampstand.auth.OnboardingProfileScreen
import com.example.lampstand.dashboard.DashboardScreen
import com.example.lampstand.data.ServiceLocator
import com.example.lampstand.journey.JourneyScreen
import com.example.lampstand.paths.ContinuePathScreen
import com.example.lampstand.paths.FandAPathScreen
import com.example.lampstand.paths.PathsScreen
import com.example.lampstand.profile.ProfileEditScreen
import com.example.lampstand.profile.ProfilePasswordScreen
import com.example.lampstand.profile.ProfileScreen
import com.example.lampstand.ui.theme.LampStandTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private enum class AuthRoute { SignedOut, SignUp, LogIn, Onboarding }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            LampStandApp()
        }
    }
}

/**
 * Whole app navigation host, extracted from onCreate so @Preview
 * (and interactive mode) renders the REAL flow instead of a
 * static screen. Activity stays thin.
 */
@Composable
fun LampStandApp(skipSplash: Boolean = false) {
    LampStandTheme(dynamicColor = false) {
                // Hoisted so splash timing never resets the auth destination
                // (keeps interactive preview + config changes from sticking on Loading).
                var splashDone by remember(skipSplash) { mutableStateOf(skipSplash) }
                LaunchedEffect(skipSplash) {
                    if (!skipSplash) {
                        delay(3000)
                        splashDone = true
                    }
                }
                // Mock backend (data/FakeAuthRepository via ServiceLocator,
                // persisted to SharedPreferences so accounts survive restarts).
                // Swap in the real AuthRepository later - screens stay untouched.
                val context = LocalContext.current
                ServiceLocator.init(context)
                val auth = remember { ServiceLocator.authRepository }
                val currentUser by auth.currentUser.collectAsState()
                var route by remember { mutableStateOf(AuthRoute.SignedOut) }
                var pendingCredentials by remember { mutableStateOf<Pair<String, String>?>(null) }
                // Shown for 3s after a successful sign-in / sign-up, before the dashboard.
                var sessionLoading by remember { mutableStateOf(false) }
                // Popup (title + message) for auth failures - e.g. wrong email/password.
                var authDialog by remember { mutableStateOf<Pair<String, String>?>(null) }
                val scope = rememberCoroutineScope()
                fun authError(message: String) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
                authDialog?.let { (title, message) ->
                    AlertDialog(
                        onDismissRequest = { authDialog = null },
                        confirmButton = {
                            TextButton(onClick = { authDialog = null }) { Text("OK") }
                        },
                        title = { Text(title) },
                        text = { Text(message) }
                    )
                }
                if (!splashDone || sessionLoading) {
                    LoadingScreen()
                } else if (currentUser == null) {
                    // ---- Signed-out: auth flow (driven by AuthRepository) ----
                    when (route) {
                        AuthRoute.SignedOut -> DashboardSignedOutScreen(
                            onItemSelected = { route = AuthRoute.LogIn },
                            onNotificationClick = { route = AuthRoute.LogIn },
                            onContinueReading = { route = AuthRoute.LogIn },
                            onRecommendedClick = { route = AuthRoute.LogIn },
                            onSignInClick = { route = AuthRoute.LogIn }
                        )
                        AuthRoute.LogIn -> LoginScreen(
                                onLogIn = { email, password ->
                                    scope.launch {
                                        auth.logIn(email, password)
                                            .onSuccess {
                                                sessionLoading = true
                                                delay(3000)
                                                sessionLoading = false
                                            }
                                            .onFailure {
                                                authDialog = "Login failed" to (it.message ?: "Login failed")
                                            }
                                    }
                                },
                                onGoogleLogIn = { authError("Google sign-in plugs in with the real backend") },
                                onForgotPassword = { authError("Password reset plugs in with the real backend") },
                                onCreateAccount = { route = AuthRoute.SignUp }
                            )
                            AuthRoute.Onboarding -> OnboardingProfileScreen(
                                onFinish = { first, last, phone, _, _, _ ->
                                    val creds = pendingCredentials ?: return@OnboardingProfileScreen
                                    scope.launch {
                                        auth.signUp(first, last, creds.first, phone, creds.second)
                                            .onSuccess {
                                                sessionLoading = true
                                                delay(3000)
                                                sessionLoading = false
                                            }
                                            .onFailure { authDialog = "Sign up failed" to (it.message ?: "Sign up failed") }
                                    }
                                }
                            )
                            AuthRoute.SignUp -> CreateAccountScreen(
                                onContinue = { email, password ->
                                    pendingCredentials = email to password
                                    route = AuthRoute.Onboarding
                                },
                                onGoogleSignUp = { authError("Google sign-up plugs in with the real backend") },
                                onLogIn = { route = AuthRoute.LogIn }
                            )
                        }
                    } else {
                        // ---- Signed-in: main app, fed by the session ----
                        currentUser?.let { user ->
                            var selectedTab by remember { mutableStateOf(0) }
                            var showFandA by remember { mutableStateOf(false) }
                            var showContinuePath by remember { mutableStateOf(false) }
                            var showProfileEdit by remember { mutableStateOf(false) }
                            var showProfilePassword by remember { mutableStateOf(false) }
                            if (showProfilePassword) {
                                ProfilePasswordScreen(
                                    onBack = { showProfilePassword = false },
                                    onItemSelected = {
                                        showProfilePassword = false
                                        selectedTab = it
                                    },
                                    onSave = { _, _ -> showProfilePassword = false }
                                )
                            } else if (showProfileEdit) {
                                ProfileEditScreen(
                                    initialFirstName = user.firstName,
                                    initialLastName = user.lastName,
                                    initialPhone = user.phone,
                                    onBack = { showProfileEdit = false },
                                    onItemSelected = {
                                        showProfileEdit = false
                                        selectedTab = it
                                    },
                                    onSave = { first, last, phone, _, _, _ ->
                                        scope.launch {
                                            auth.updateProfile(first, last, phone)
                                                .onSuccess { showProfileEdit = false }
                                                .onFailure { authError(it.message ?: "Save failed") }
                                        }
                                    }
                                )
                            } else if (showContinuePath) {
                                ContinuePathScreen(
                                    onBack = {
                                        showContinuePath = false
                                        showFandA = true
                                    },
                                    onItemSelected = {
                                        showContinuePath = false
                                        showFandA = false
                                        selectedTab = it
                                    }
                                )
                            } else if (showFandA) {
                                FandAPathScreen(
                                    onBack = { showFandA = false },
                                    onItemSelected = {
                                        showFandA = false
                                        selectedTab = it
                                    },
                                    onCardClick = {
                                        showFandA = false
                                        showContinuePath = true
                                    }
                                )
                            } else when (selectedTab) {
                                1 -> PathsScreen(
                                    onItemSelected = { selectedTab = it },
                                    onViewPathsClick = { showFandA = true }
                                )
                                2 -> JourneyScreen(
                                    onItemSelected = { selectedTab = it }
                                )
                                3 -> ProfileScreen(
                                    name = "${user.firstName} ${user.lastName}".trim().ifBlank { user.email },
                                    email = user.email,
                                    initials = (user.firstName.take(1) + user.lastName.take(1))
                                        .uppercase().ifBlank { "?" },
                                    onItemSelected = { selectedTab = it },
                                    onPersonalInfoClick = { showProfileEdit = true },
                                    onManagePasswordClick = { showProfilePassword = true },
                                    onLogOutClick = { scope.launch { sessionLoading = false; auth.logOut(); route = AuthRoute.SignedOut } }
                                )
                                else -> DashboardScreen(
                                    onItemSelected = { selectedTab = it },
                                    userName = user.firstName.ifBlank { "ANIBE" },
                                    onContinueClick = { selectedTab = 1 }
                                )
                            }
                        }
                    }
                }
            }

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainActivityPreview() {
    // skipSplash = true so interactive preview starts on the
    // signed-out dashboard immediately instead of sitting on Loading.
    // Set to false to preview the 3-second splash.
    LampStandApp(skipSplash = true)
}
