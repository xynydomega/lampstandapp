package com.example.lampstand.journey

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.dashboard.BottomNavBar
import com.example.lampstand.ui.theme.LampStandTheme

private val Navy = Color(0xFF184159)
private val Blue = Color(0xFF335E78)

@Composable
private fun AmountChip(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(52.dp)
            .border(
                width = if (selected) 1.5.dp else 0.5.dp,
                color = if (selected) Blue else Color(0xFFB3B3B3),
                shape = RoundedCornerShape(6.dp)
            )
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFFCFCFC))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onSelect
            )
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (selected) Blue else Color(0xFF8A8A8A)
        )
    }
}

/**
 * Donation screen (design/donationscreen.png):
 * "Support the Mission" — one-time gift via Stripe.
 */
@Composable
fun JourneyDonationScreen(
    onItemSelected: (Int) -> Unit = {},
    onBack: () -> Unit = {},
    onGive: (amount: String) -> Unit = {},
    onMaybeLater: () -> Unit = {}
) {
    var selectedAmount by remember { mutableStateOf(0) }
    var customAmount by remember { mutableStateOf("") }
    val presets = listOf("$5", "$15", "$30")

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
                .padding(top = 12.dp, bottom = 24.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onBack
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = Blue,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_read_cv_logo),
                    contentDescription = null,
                    tint = Color(0xFF92ADBE),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "SUPPORT",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 14.sp,
                    letterSpacing = 1.sp,
                    color = Color(0xFF92ADBE)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Support the Mission",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 32.sp,
                color = Navy
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Lampstand is free right now because we believe formation should be accessible to every believer. We're a small team building something we believe matters deeply. If this journey has been meaningful to you, consider supporting what we're building.",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                color = Navy
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "1.) One-Time Gift",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp,
                color = Navy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "A simple way to support the mission",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                color = Navy
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                presets.forEachIndexed { index, label ->
                    AmountChip(
                        label = label,
                        selected = selectedAmount == index && customAmount.isBlank(),
                        onSelect = {
                            selectedAmount = index
                            customAmount = ""
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "Give Another Amount",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                color = Color(0xFF535353)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = customAmount,
                onValueChange = { customAmount = it },
                placeholder = { Text("Enter Amount", color = Color(0xFF8A8A8A), fontSize = 15.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Color(0xFFD0D7DC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Navy,
                    unfocusedTextColor = Navy
                ),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val amount = if (customAmount.isNotBlank()) customAmount
                    else presets[selectedAmount]
                    onGive(amount)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text(
                    text = "Give with ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    text = "stripe",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(72.dp))
            Text(
                text = "** No pressure. Every amount helps. You can also skip and come back to this anytime.",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                color = Navy
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(
                onClick = onMaybeLater,
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Blue),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text(
                    "Maybe Later",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Blue
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
        BottomNavBar(selectedIndex = 2, onItemSelected = onItemSelected)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JourneyDonationScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        JourneyDonationScreen()
    }
}
