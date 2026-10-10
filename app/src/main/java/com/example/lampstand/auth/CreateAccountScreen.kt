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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.ui.components.LampButton
import com.example.lampstand.ui.components.LampOutlinedButton
import com.example.lampstand.ui.theme.LampStandTheme

private val Blue = Color(0xFF335E78)
private val BodyGrey = Color(0xFF5A5A5A)
private val FieldBorder = Color(0xFFD0D7DC)

@Composable
private fun AuthFieldLabel(text: String) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp,
        color = BodyGrey
    )
}

/**
 * Create account screen (design/CreateAccountscreen.png, corrected order):
 * email -> password -> OR -> Google sign-up -> Continue -> Log in.
 */
@Composable
fun CreateAccountScreen(
    onContinue: (email: String, password: String) -> Unit = { _, _ -> },
    onGoogleSignUp: () -> Unit = {},
    onLogIn: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val canContinue = email.contains("@") && password.isNotBlank()

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
                text = "Create Your Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 30.sp,
                color = Blue
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Enter your email address to create your account",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                color = BodyGrey
            )
            Spacer(modifier = Modifier.height(32.dp))

            // 1.) Email address.
            AuthFieldLabel("Email Address")
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

            // 2.) Password.
            AuthFieldLabel("Password")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter your Password", color = Color(0xFF8A8A8A), fontSize = 16.sp) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
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
                modifier = Modifier.fillMaxWidth().height(58.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // 3.) OR divider.
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

            // 4.) Sign up with Google.
            LampOutlinedButton(
                onClick = onGoogleSignUp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Continue with Google",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = BodyGrey
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            // 5.) Continue — blue once email + password are filled.
            LampButton(
                text = "Continue",
                onClick = { onContinue(email, password) },
                enabled = canContinue,
                modifier = Modifier.fillMaxWidth().height(52.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 6.) Log in.
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 14.sp,
                    color = BodyGrey
                )
                Text(
                    text = "Log in",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onLogIn
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = buildAnnotatedString {
                    append("By clicking 'continue', you agree to our ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("terms and conditions")
                    }
                    append(" and ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("privacy policy")
                    }
                },
                fontSize = 13.sp,
                lineHeight = 18.sp,
                color = BodyGrey,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateAccountScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        CreateAccountScreen()
    }
}
