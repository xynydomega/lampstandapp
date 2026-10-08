package com.example.lampstand.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import com.example.lampstand.R
import com.example.lampstand.dashboard.BottomNavBar
import com.example.lampstand.ui.theme.LampStandTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

private data class WeekDay(
    val name: String,
    val number: String,
    val isToday: Boolean
)

/**
 * Current week starting Monday, so the calendar keeps counting
 * with real dates instead of hardcoded values.
 */
@Composable
private fun rememberWeekDays(count: Int = 6): List<WeekDay> {
    return remember {
        val today = LocalDate.now()
        val monday = today.with(DayOfWeek.MONDAY)
        (0 until count).map { offset ->
            val date = monday.plusDays(offset.toLong())
            WeekDay(
                name = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                number = date.dayOfMonth.toString(),
                isToday = date.isEqual(today)
            )
        }
    }
}

/**
 * Single session row: 334 wide (min 49 tall), gap 15.
 * Left group 203 wide (asterisk 12 + gap 11 + text 180 wide),
 * solid play icon 18 x 18 at the end. Titles wrap to 2 lines
 * where the spec calls for 34 height / 17 line-height.
 */
@Composable
private fun PathSessionRow(
    sessionLabel: String,
    title: String,
    titleLineHeight: TextUnit = 15.sp,
    titleMaxLines: Int = 1,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .width(334.dp)
            .heightIn(min = 49.dp)
    ) {
        // Left group: 203 wide, gap 11.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            modifier = Modifier
                .width(203.dp)
                .heightIn(min = 34.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.asterisk),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(12.dp)
            )
            // Text frame: 180 wide, gap 4.
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.width(180.dp)
            ) {
                // Heading: 54 x 15, Inter 400 Regular 12/15, #535353.
                Text(
                    text = sessionLabel,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF535353),
                    maxLines = 1,
                    modifier = Modifier
                        .width(54.dp)
                        .height(15.dp)
                )
                // Subcontent: 180 wide, Inter 600 SemiBold 14sp, #1E1E1E.
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = titleLineHeight,
                    letterSpacing = 0.sp,
                    color = Color(0xFF1E1E1E),
                    maxLines = titleMaxLines,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    modifier = Modifier.width(180.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        // Solid play icon (drawn, 18 x 18): dark circle + white triangle.
        // Drawn instead of a PNG so no resource-cache issues can occur.
        Canvas(modifier = Modifier.size(18.dp)) {
            drawCircle(color = Color(0xFF1E1E1E))
            val w = size.width
            val h = size.height
            drawPath(
                path = Path().apply {
                    moveTo(w * 0.38f, h * 0.30f)
                    lineTo(w * 0.70f, h * 0.50f)
                    lineTo(w * 0.38f, h * 0.70f)
                    close()
                },
                color = Color.White
            )
        }
    }
}

/**
 * Last screen of the Paths flow: path detail with header,
 * reading schedule, overview and sessions.
 */
@Composable
fun ContinuePathScreen(
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(466.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 32.dp,
                            bottomEnd = 32.dp
                        )
                    )
                    .background(Color(0x26335E78))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 20.dp, top = 20.dp)
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
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 92.dp, top = 106.dp)
                        .width(186.dp)
                        .height(206.dp)
                        .background(Color(0xFFFCFCFC))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.journey_illustration),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                // Heading underneath image: 197 x 24, top 342, left 89,
                // Inter 600 SemiBold 20/24, center, #184159.
                Text(
                    text = "Trust in Uncertainty",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF184159),
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 89.dp, top = 342.dp)
                        .width(197.dp)
                        .height(24.dp)
                )
                // Sub content beneath heading: 205 x 45, top 367, left 85,
                // Inter 400 Regular 12/15, center, #535353.
                Text(
                    text = "Learn to rest in God\u2019s unchanging character even when the path ahead is unclear or unsettling.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF535353),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 85.dp, top = 367.dp)
                        .width(205.dp)
                        .height(45.dp)
                )
            }
            // CTA button overlapping header bottom: 275 x 42 at content-top 445
            // (screen-top 489), left 59, radius 8, #335E78.
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 59.dp, top = 445.dp)
                    .width(275.dp)
                    .height(42.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF335E78))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Continue Reading",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 22.sp,
                        letterSpacing = 0.sp,
                        color = Color.White,
                        maxLines = 1,
                        modifier = Modifier
                            .width(119.dp)
                            .height(22.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ion_play_back),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            // Reading Schedule frame: 358 x 93 at content-top 529
            // (screen-top 573), left 19, gap 19.
            Column(
                verticalArrangement = Arrangement.spacedBy(19.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp, top = 529.dp)
                    .width(358.dp)
                    .height(93.dp)
            ) {
                // Title: 358 x 15, Inter 600 SemiBold 14/15, #535353.
                Text(
                    text = "Reading Schedule",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF535353),
                    maxLines = 1,
                    modifier = Modifier
                        .width(358.dp)
                        .height(15.dp)
                )
                // Week calendar row: 358 x 59, gap 8.
                // 6 cells of 53 fit exactly: 6*53 + 5*8 = 358.
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .width(358.dp)
                        .height(59.dp)
                ) {
                    val weekDays = rememberWeekDays(count = 6)
                    weekDays.forEach { day ->
                        // Inverted: the current day is lighter, former/other
                        // days stay solid.
                        val isCurrent = day.isToday
                        Box(
                            modifier = Modifier
                                .width(53.dp)
                                .height(59.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isCurrent) Color(0x26335E78)
                                    else Color(0xFF335E78)
                                )
                        ) {
                            // Day name: 25 x 15, top 8, left 15,
                            // Inter 400 Regular 12/15.
                            Text(
                                text = day.name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 15.sp,
                                letterSpacing = 0.sp,
                                color = if (isCurrent) Color(0xFF335E78) else Color.White,
                                maxLines = 1,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(start = 15.dp, top = 8.dp)
                                    .width(25.dp)
                                    .height(15.dp)
                            )
                            // Day number: top 32, left 21,
                            // Inter 500 Medium 16/15.
                            Text(
                                text = day.number,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 15.sp,
                                letterSpacing = 0.sp,
                                color = if (isCurrent) Color(0xFF335E78) else Color.White,
                                maxLines = 1,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(start = 21.dp, top = 32.dp)
                                    .width(20.dp)
                                    .height(15.dp)
                            )
                        }
                    }
                }
            }
            // Overview frame: 358 x 93 at content-top 649 (27dp beneath
            // the schedule frame which ends at 622), left 19, gap 19.
            Column(
                verticalArrangement = Arrangement.spacedBy(19.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp, top = 649.dp)
                    .width(358.dp)
                    .height(93.dp)
            ) {
                // Inner frame: 334 x 77, gap 8.
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .width(334.dp)
                        .height(77.dp)
                ) {
                    // Heading: 334 x 15, Inter 600 SemiBold 16/15, #535353.
                    Text(
                        text = "Overview",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 15.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFF535353),
                        maxLines = 1,
                        modifier = Modifier
                            .width(334.dp)
                            .height(15.dp)
                    )
                    // Subcontent: 334 x 54, Inter 400 Regular 13/18, #535353.
                    Text(
                        text = "This 7-day path is designed to help you rely on God\u2019s steady presence and character when life feels unclear or unpredictable.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 18.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFF535353),
                        modifier = Modifier
                            .width(334.dp)
                            .height(54.dp)
                    )
                }
            }
            // Day-list frame: 334 wide at content-top 773 (31dp beneath
            // the Overview frame which ends at 742), left 19, gap 20.
            // Height wraps content so all 7 sessions stay visible.
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 19.dp, top = 773.dp)
                    .width(334.dp)
            ) {
                // Sessions title: 334 x 15, Inter 600 SemiBold 16/15, #535353.
                Text(
                    text = "Sessions",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF535353),
                    maxLines = 1,
                    modifier = Modifier
                        .width(334.dp)
                        .height(15.dp)
                )
                // Sessions list: 334 wide, gap 10. Wraps content so all
                // 7 rows + dividers stay visible (spec 443 fits ~5 rows).
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.width(334.dp)
                ) {
                    // All 7 sessions with their titles. Sessions 5 and 7
                    // wrap to 2 lines per spec (34 height / 17 line-height).
                    val sessions = listOf(
                        Triple("Session 1", "God Sees the Path Ahead", 15.sp to 1),
                        Triple("Session 2", "His Timing is Never Late", 15.sp to 1),
                        Triple("Session 3", "Guidance in the Wilderness", 15.sp to 1),
                        Triple("Session 4", "Finding Stillness", 15.sp to 1),
                        Triple("Session 5", "Trusting Beyond What You Feel", 17.sp to 2),
                        Triple("Session 6", "Letting Go of Self-Control", 17.sp to 1),
                        Triple("Session 7", "Resting in God\u2019s Sovereignty", 17.sp to 2)
                    )
                    sessions.forEachIndexed { index, (label, sessionTitle, style) ->
                        PathSessionRow(
                            sessionLabel = label,
                            title = sessionTitle,
                            titleLineHeight = style.first,
                            titleMaxLines = style.second
                        )
                        // Hairline divider under every session, including 7:
                        // 334 wide, 0.5px solid #B3B3B3.
                        HorizontalDivider(
                            thickness = 0.5.dp,
                            color = Color(0xFFB3B3B3),
                            modifier = Modifier.width(334.dp)
                        )
                    }
                }
                // Free space at the bottom so the last session
                // doesn't sit flush against the bottom bar.
                Spacer(modifier = Modifier.height(48.dp))
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
fun ContinuePathScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        ContinuePathScreen()
    }
}
