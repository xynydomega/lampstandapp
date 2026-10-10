package com.example.lampstand.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.ui.theme.LampStandTheme

private val Blue = Color(0xFF335E78)
private val BodyGrey = Color(0xFF5A5A5A)
private val FieldBorder = Color(0xFFD0D7DC)

@Composable
private fun LoginFieldLabel(text: String) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp,
        color = BodyGrey
    )
}

/**
 * Login screen — mirror of [CreateAccountScreen]:
 * email -> password -> Log In -> OR -> Google -> create-account link.
 */
@Composable
fun LoginScreen(
    onLogIn: (email: String, password: String) -> Unit = { _, _ -> },
    onGoogleLogIn: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onCreateAccount: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 24.dp, bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lampstand_logo_blue_back_1),
                contentDescription = "Lampstand",
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Welcome Back",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 30.sp,
                color = Blue
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Log in to continue your journey",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                color = BodyGrey
            )
            Spacer(modifier = Modifier.height(32.dp))

            // 1.) Email address.
            LoginFieldLabel("Email Address")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Enter your Email", color = Color(0xFF8A8A8A), fontSize = 16.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = FieldBorder,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = BodyGrey,
                    unfocusedTextColor = BodyGrey
                ),
                modifier = Modifier.fillMaxWidth().height(58.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // 2.) Password + forgot-password link.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                LoginFieldLabel("Password")
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Forgot password?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Blue,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onForgotPassword
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter your Password", color = Color(0xFF8A8A8A), fontSize = 16.sp) },
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = FieldBorder,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = BodyGrey,
                    unfocusedTextColor = BodyGrey
                ),
                trailingIcon = {
                    Text(
                        text = if (showPassword) "Hide" else "Show",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Blue,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { showPassword = !showPassword }
                            )
                    )
                },
                modifier = Modifier.fillMaxWidth().height(58.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // 3.) Log In.
            Button(
                onClick = { onLogIn(email, password) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text(
                    "Log In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            // 4.) OR divider.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFF8A8A8A))
                Text(
                    text = "OR",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = BodyGrey,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFF8A8A8A))
            }
            Spacer(modifier = Modifier.height(24.dp))

            // 5.) Log in with Google.
            OutlinedButton(
                onClick = onGoogleLogIn,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(0.5.dp, FieldBorder),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Log in with Google",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = BodyGrey
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            // 6.) Create-account link.
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "New here? ",
                    fontSize = 14.sp,
                    color = BodyGrey
                )
                Text(
                    text = "Create account",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCreateAccount
                    )
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        LoginScreen()
    }
}
