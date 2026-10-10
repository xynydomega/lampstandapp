package com.example.lampstand.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.profile.ReminderTimeCards
import com.example.lampstand.profile.defaultReminderTimes
import com.example.lampstand.ui.components.CountryCodePicker
import com.example.lampstand.ui.components.LampBlue
import com.example.lampstand.ui.components.LampButton
import com.example.lampstand.ui.components.defaultCountry
import com.example.lampstand.ui.components.formatFullPhone
import com.example.lampstand.ui.theme.LampStandTheme
import kotlinx.coroutines.launch

private val BodyGrey = Color(0xFF5A5A5A)
private val FieldBorder = Color(0xFFD0D7DC)

@Composable
private fun OnboardingFieldLabel(text: String) {
    Text(
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp,
        color = BodyGrey
    )
}

@Composable
private fun OnboardingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    leading: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = LampBlue,
            unfocusedBorderColor = FieldBorder,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = BodyGrey,
            unfocusedTextColor = BodyGrey
        ),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        leadingIcon = leading,
        modifier = Modifier.fillMaxWidth().height(58.dp)
    )
}

@Composable
private fun NigerianFlag() {
    // Kept for previews only; the live field uses CountryCodePicker (flag emoji + dial).
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(9.dp).height(14.dp).background(Color(0xFF008751)))
        Box(modifier = Modifier.width(9.dp).height(14.dp).background(Color.White).border(0.5.dp, FieldBorder))
        Box(modifier = Modifier.width(9.dp).height(14.dp).background(Color(0xFF008751)))
    }
}

/**
 * Onboarding profile setup: profile fields + meditation reminder
 * (toggle starts ON) + Finish. No bottom nav — part of the
 * onboarding flow, not a tab screen.
 *
 * Finish stays grey until first + last name are filled; phone and
 * reminder are optional.
 */
@Composable
fun OnboardingProfileScreen(
    onFinish: (firstName: String, lastName: String, phone: String, reminderEnabled: Boolean, slot: Int, times: Map<Int, Pair<Int, Int>>) -> Unit =
        { _, _, _, _, _, _ -> }
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var country by remember { mutableStateOf(defaultCountry()) }
    val reminderEnabled = true
    var selectedSlot by remember { mutableStateOf(1) }
    var times by remember { mutableStateOf(defaultReminderTimes()) }
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val canFinish = firstName.isNotBlank() && lastName.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
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
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Let's set up your profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 30.sp,
                color = LampBlue
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Tell us about you and pick a daily reminder time",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                color = BodyGrey
            )
            Spacer(modifier = Modifier.height(32.dp))

            OnboardingFieldLabel("First Name")
            Spacer(modifier = Modifier.height(8.dp))
            OnboardingTextField(value = firstName, onValueChange = { firstName = it })
            Spacer(modifier = Modifier.height(20.dp))
            OnboardingFieldLabel("Last Name")
            Spacer(modifier = Modifier.height(8.dp))
            OnboardingTextField(value = lastName, onValueChange = { lastName = it })
            Spacer(modifier = Modifier.height(20.dp))
            OnboardingFieldLabel("Phone Number")
            Spacer(modifier = Modifier.height(8.dp))
            OnboardingTextField(
                value = phone,
                onValueChange = { phone = it },
                keyboardType = KeyboardType.Phone,
                leading = {
                    CountryCodePicker(
                        selected = country,
                        onSelect = { country = it }
                    )
                }
            )
            Spacer(modifier = Modifier.height(32.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Daily Reminder",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp,
                    color = BodyGrey
                )
                Text(
                    text = "A gentle nudge for your daily session",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    color = BodyGrey
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Meditation time",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp,
                    color = BodyGrey
                )
                Spacer(modifier = Modifier.height(8.dp))
                ReminderTimeCards(
                    times = times,
                    selectedSlot = selectedSlot,
                    onSlotSelect = { selectedSlot = it },
                    onTimeChange = { slot, hour, minute ->
                        times = times.toMutableMap().apply { put(slot, hour to minute) }
                    },
                    onTimePicked = { title, label ->
                        scope.launch { snackbar.showSnackbar("$title set for $label") }
                    }
                )
            Spacer(modifier = Modifier.height(32.dp))
            LampButton(
                text = "Finish",
                onClick = {
                    onFinish(firstName, lastName, formatFullPhone(country, phone), reminderEnabled, selectedSlot, times)
                },
                enabled = canFinish,
                modifier = Modifier.fillMaxWidth().height(52.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
        }
        SnackbarHost(
            hostState = snackbar,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 48.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingProfileScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        OnboardingProfileScreen()
    }
}
