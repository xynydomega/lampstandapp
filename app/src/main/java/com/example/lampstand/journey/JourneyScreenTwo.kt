package com.example.lampstand.journey

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.dashboard.BottomNavBar
import com.example.lampstand.ui.theme.LampStandTheme

@Composable
fun JourneyScreenTwo(
    onItemSelected: (Int) -> Unit = {},
    onBack: () -> Unit = {}
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
            // Back button: 30 x 30, top 40, left 19, radius 16, white.
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp, top = 40.dp)
                    .size(30.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onBack
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = Color(0xFF335E78),
                    modifier = Modifier.size(16.dp)
                )
            }
            // First frame: 264 wide, top 125, left 19, gap 9.
            // Height wraps so the two-line heading never clips.
            Column(
                verticalArrangement = Arrangement.spacedBy(9.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp, top = 125.dp)
                    .width(264.dp)
            ) {
                // Frame 1: readcv icon + CONTEXT.
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .width(264.dp)
                        .height(14.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_read_cv_logo),
                        contentDescription = null,
                        tint = Color(0xFF92ADBE),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "CONTEXT",
                        fontSize = 11.sp,
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
                // Frame 2: heading, 247 wide, 20/26, #184159.
                // Height wraps (spec 42 clips two 26dp lines).
                Text(
                    text = "Historical & Biblical Background",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 26.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF184159),
                    modifier = Modifier.width(247.dp)
                )
            }
            // Second frame: 336 x 750, top 226, left 19, gap 60.
            Column(
                verticalArrangement = Arrangement.spacedBy(60.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp, top = 226.dp)
                    .width(336.dp)
                    .height(750.dp)
            ) {
                // Body: 336 x 660, Inter 400 Regular 14/20, #535353.
                Text(
                    text = "Psalm 46 was written in a time when God\u2019s people were familiar with national turmoil, threats from surrounding nations, and the instability of life in the ancient Near East. This psalm belongs to a collection attributed to the sons of Korah, a Levitical family responsible for worship in the temple. Their writings often reflect deep trust in God\u2019s protection during seasons of crisis. Psalm 46 in particular was composed to strengthen the hearts of God\u2019s people when everything around them felt unstable\u2014politically, militarily, and emotionally.\n\nThe command \u201CBe still\u201D in Hebrew (raphah) does not simply mean \u201Cbe quiet.\u201D It means to loosen, to let go, to relax your grip. God is speaking into a context of fear and unrest, calling His people not to panic, not to strive, and not to attempt to control what they cannot control. Instead, He directs them to recognize His unmatched power and sovereignty: \u201CKnow that I am God.\u201D This is a declaration of divine authority in the middle of human uncertainty.\n\nThe psalm also uses vivid imagery\u2014roaring waters, shaking mountains, nations in uproar\u2014to show that even when the world feels chaotic, God stands firm. The message is that true stillness is not found in calm circumstances; it is found in a steady God. Historically, this verse served as an anchor for Israel during times of conflict, reminding them that God, not their circumstances, defined their security.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF535353),
                    modifier = Modifier
                        .width(336.dp)
                        .height(660.dp)
                )
                // Frame 1 (nav): prev + dots + next, 30 tall.
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .width(336.dp)
                        .height(30.dp)
                ) {
                    // Frame I: prev button, 30 x 30, radius 16, white.
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onBack
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Previous",
                            tint = Color(0xFF335E78),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    // Frame II: dots, 74 x 10, gap 6 — second highlighted.
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
                                        if (index == 1) Color(0xFF335E78)
                                        else Color(0xFFB3B3B3)
                                    )
                            )
                        }
                    }
                    // Frame III: next button, 30 x 30, arrow rotated -180.
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {}
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Next",
                            tint = Color(0xFF335E78),
                            modifier = Modifier
                                .size(16.dp)
                                .rotate(180f)
                        )
                    }
                }
            }
        }

        BottomNavBar(
            selectedIndex = 2,
            onItemSelected = onItemSelected
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JourneyScreenTwoPreview() {
    LampStandTheme(dynamicColor = false) {
        JourneyScreenTwo()
    }
}
