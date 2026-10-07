package com.example.lampstand.paths

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.dashboard.BottomNavBar
import com.example.lampstand.ui.theme.LampStandTheme

@Composable
fun FandAPathScreen(
    onBack: () -> Unit = {},
    onItemSelected: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        PathsTopBar(
            title = "",
            onBackClick = onBack
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Title: 217 x 24, top 118, left 21
        Text(
            text = "Fear & Anxiety",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            color = Color(0xFF335E78),
            maxLines = 1,
            modifier = Modifier
                .padding(start = 21.dp)
                .width(217.dp)
                .height(24.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Subtitle: 315 x 60, top 152, left 21
        Text(
            text = "Grow in understanding God’s character through these curated paths focused on Fear and Anxiety",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            letterSpacing = 0.sp,
            color = Color(0xFF535353),
            modifier = Modifier
                .padding(start = 21.dp, end = 21.dp)
                .width(315.dp)
                .height(60.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // List container: 339 x 876, top 240, left 19.
                // Gap: 32dp after search bar, 12dp between cards.
                Column(
                    modifier = Modifier
                        .padding(start = 19.dp, top = 8.dp, bottom = 16.dp)
                        .width(339.dp)
                        .height(876.dp)
                ) {
                    PathSearchBar()
                    Spacer(modifier = Modifier.height(32.dp))
                    val cardImages = listOf(
                        com.example.lampstand.R.drawable.shipsail,
                        com.example.lampstand.R.drawable.bible,
                        com.example.lampstand.R.drawable.dove,
                        com.example.lampstand.R.drawable.recommended,
                        com.example.lampstand.R.drawable.crown,
                        com.example.lampstand.R.drawable.shipsail
                    )
                    cardImages.forEachIndexed { index, imageRes ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        FeaturedPathCard(imageRes = imageRes)
                    }
                }
            }
        }

        BottomNavBar(
            selectedIndex = 1,
            onItemSelected = onItemSelected
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FandAPathScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        FandAPathScreen()
    }
}
