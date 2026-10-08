package com.example.lampstand.journey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.dashboard.BottomNavBar
import com.example.lampstand.ui.theme.LampStandTheme

@Composable
fun JourneyScreen(
    onItemSelected: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Hero image: 335 x 148 — top 88, 20 both sides
            // (fillMaxWidth minus 40), 36 gap to the frame below (ends 236).
            Image(
                painter = painterResource(id = R.drawable.shipanchor),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 88.dp, end = 20.dp)
                    .height(148.dp)
            )
            // Frame underneath image: 263 x 14, top 272, left 21, gap 4.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 21.dp, top = 272.dp)
                    .width(263.dp)
                    .height(14.dp)
            ) {
                // ReadCV logo: 12 x 12.
                Icon(
                    painter = painterResource(id = R.drawable.ic_read_cv_logo),
                    contentDescription = null,
                    tint = Color(0xFF92ADBE),
                    modifier = Modifier.size(12.dp)
                )
                // Title: 247 x 14, Inter 600 SemiBold 12/14, #92ADBE.
                Text(
                    text = "TODAY\u2019S JOURNEY",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF92ADBE),
                    maxLines = 1,
                    modifier = Modifier
                        .width(247.dp)
                        .height(14.dp)
                )
            }
            // Heading: 247 x 33, top 291, left 20,
            // Inter 600 SemiBold 28/32, #184159.
            Text(
                text = "Finding Stillness",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
                color = Color(0xFF184159),
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 291.dp)
                    .width(247.dp)
                    .height(33.dp)
            )
            // Frame 1: 335 x 687, top 338, left 20, gap 32.
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 338.dp)
                    .width(335.dp)
                    .height(687.dp)
            ) {
                // Intro: 335 x 60, Inter 400 Regular 14/20, #535353.
                Text(
                    text = "Finding Stillness invites you to quiet your heart, slow your mind, and become aware of God\u2019s steady presence in the midst of life\u2019s noise.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF535353),
                    modifier = Modifier
                        .width(335.dp)
                        .height(60.dp)
                )
                // Scripture card: 335 x 127, radius 4, padding 8/16,
                // 3px left border #335E78, background #335E78 at 10%.
                Box(
                    modifier = Modifier
                        .width(335.dp)
                        .height(127.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x1A335E78))
                ) {
                    // Left border bar: 3dp wide, rounded on the left
                    // to follow the card's corners.
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(3.dp)
                            .fillMaxHeight()
                            .clip(
                                RoundedCornerShape(
                                    topStart = 4.dp,
                                    bottomStart = 4.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 0.dp
                                )
                            )
                            .background(Color(0xFF335E78))
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(11.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        // Title: 300 x 15, Inter 600 SemiBold 14/15, #335E78.
                        Text(
                            text = "CORE SCRIPTURE",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 15.sp,
                            letterSpacing = 0.sp,
                            color = Color(0xFF335E78),
                            maxLines = 1,
                            modifier = Modifier
                                .width(300.dp)
                                .height(15.dp)
                        )
                        // Verse frame: 300 x 85, gap 7.
                        Column(
                            verticalArrangement = Arrangement.spacedBy(7.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .height(85.dp)
                        ) {
                            // Verse: 300 x 60, Inter 400 Regular 14/20, #535353.
                            Text(
                                text = "\u201CBe still, and know that I am God; I will be exalted among the nations, I will be exalted in the earth.\u201D",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                color = Color(0xFF535353),
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(60.dp)
                            )
                            // Reference: 300 x 18, Inter 600 SemiBold 14/18, #335E78.
                            Text(
                                text = "Psalm 46:10 (NIV)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 18.sp,
                                letterSpacing = 0.sp,
                                color = Color(0xFF335E78),
                                maxLines = 1,
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(18.dp)
                            )
                        }
                    }
                }
                // Body frame: 335 x 436, gap 86.
                // 60 intro + 32 + 127 card + 32 + 436 body = 687 frame.
                Column(
                    verticalArrangement = Arrangement.spacedBy(86.dp),
                    modifier = Modifier
                        .width(335.dp)
                        .height(436.dp)
                ) {
                    // Body: 335 x 320, Inter 400 Regular 14/20, #535353.
                    // Fixed 320 per spec so the nav row and 201 space
                    // below land exactly (320 + 86 + 30 = 436).
                    Text(
                        text = "When God says, \u201CBe still,\u201D He is inviting you to release your grip on outcomes, on worries, on the need to figure everything out. Stillness is an act of trust. It is choosing to pause long enough to remember that God is both powerful and present\u2014that He holds your story, sees your situation, and is not overwhelmed by what overwhelms you. In stillness, we stop carrying what He has already offered to carry.\n\nAs you walk through this session, allow your heart to slow down. Let the noise fall away long enough to hear the truth again: God is near, God is steady, and God is God. Stillness is not weakness; it is the posture that makes room for His peace to settle in your soul.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFF535353),
                        overflow = androidx.compose.ui.text.style.TextOverflow.Clip,
                        modifier = Modifier
                            .width(335.dp)
                            .height(320.dp)
                    )
                    // Nav row 86dp below body: 215 x 30, space-between,
                    // 120dp left padding (120 + 215 = 335, flush right).
                    // 320 body + 86 + 30 nav = 436 body frame.
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(start = 120.dp)
                            .width(215.dp)
                            .height(30.dp)
                    ) {
                        // Dots: 74 x 10, gap 6, five 10px dots radius 20.
                        // First highlighted #335E78, rest #B3B3B3.
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .width(74.dp)
                                .height(10.dp)
                        ) {
                            repeat(5) { index ->
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(
                                            if (index == 0) Color(0xFF335E78)
                                            else Color(0xFFB3B3B3)
                                        )
                                )
                            }
                        }
                        // Next arrow: 24 x 24, back-arrow image rotated -180.
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Next",
                            tint = Color(0xFF335E78),
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(180f)
                        )
                    }
                }
            }
            // Bottom free space: 201dp after Frame 1 (ends 1025),
            // so the last frame rests 201 above the bottom bar.
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 1025.dp)
                    .fillMaxWidth()
                    .height(201.dp)
            )
        }

        BottomNavBar(
            selectedIndex = 2,
            onItemSelected = onItemSelected
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JourneyScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        JourneyScreen()
    }
}
