package com.example.lampstand.paths

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lampstand.R
import com.example.lampstand.ui.theme.LampStandTheme

/**
 * Search bar: 339 x 40, radius 4, border 1px #E8E8E8.
 * Icon 18x18 (top 11, left 11), placeholder 111x15 "Search Topics..."
 * 14sp Regular #B3B3B3.
 */
@Composable
fun PathSearchBar(
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .width(339.dp)
            .height(40.dp)
            .border(1.dp, Color(0xFFE8E8E8), RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(start = 11.dp, end = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(18.dp)
        )
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            singleLine = true,
            cursorBrush = SolidColor(Color(0xFF335E78)),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 15.sp,
                letterSpacing = 0.sp,
                color = Color(0xFF1E1E1E)
            ),
            modifier = Modifier
                .width(111.dp)
                .height(15.dp),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (query.isEmpty()) {
                        Text(
                            text = "Search Topics...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 15.sp,
                            letterSpacing = 0.sp,
                            color = Color(0xFFB3B3B3),
                            maxLines = 1
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

/**
 * Featured path card: 339 x 124, padding 12, gap 10, radius 8,
 * border 1px #E8E8E8. Inner content 205 x 98 holding the
 * 145 x 40 block with the calendar + "7 days" badge.
 */
@Composable
fun FeaturedPathCard(
    imageRes: Int? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(339.dp)
            .height(124.dp)
            .border(1.dp, Color(0xFFE8E8E8), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Smaller container: 205 x 98, gap 10
        Column(
            modifier = Modifier
                .width(205.dp)
                .height(98.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Even smaller container: 145 x 40, gap 10
            Row(
                modifier = Modifier
                    .width(145.dp)
                    .height(40.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Single badge row (not duplicated): icon + text, wraps content
                // so "7 days" is never clipped to just "7".
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.height(15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "7 days",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 15.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFF335E78),
                        maxLines = 1
                    )
                }
            }
        }

        // Right side: 98 x 98 image, radius 4, on #1E1E1E
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(98.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF1E1E1E))
            )
        } else {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 375)
@Composable
fun FandAPathContentPreview() {
    LampStandTheme(dynamicColor = false) {
        Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
            PathSearchBar()
            FeaturedPathCard()
        }
    }
}
