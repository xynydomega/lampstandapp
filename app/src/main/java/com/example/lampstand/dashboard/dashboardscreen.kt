package com.example.lampstand.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.lampstand.R
import com.example.lampstand.ui.theme.LampStandTheme

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
    Column(
        modifier = Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState())
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
            text = "HI, USER",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            color = Color(0xFF92ADBE),
            )

            Text(
                text = "Welcome Back",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                color = Color(0xFF335E78),
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notifications",
                tint = Color(0xFF335E78),
                modifier = Modifier.size(24.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (-4).dp, y = (-4).dp)
                    .size(14.dp)
                    .background(Color(0xFFE5484D), CircleShape)
            ) {
                Text(
                    text = "1",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 10.sp,
                    color = Color.White
                )
            }
        }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(277.dp)
                .background(
                    color = Color(0xFF335E78),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, top = 24.dp, end = 13.dp)
            ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .widthIn(min = 143.dp)
                    .heightIn(min = 18.dp)
                    .background(
                        color = Color(0xFFE7F6FF),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "ACTIVE PATH . DAY 4 OF 7",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF335E78),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_read_cv_logo),
                    contentDescription = null,
                    tint = Color(0xFF92ADBE),
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "FORMATION IN PROGRESS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF92ADBE),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(13.dp))

            Text(
                text = "Trust in Uncertainty",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                color = Color(0xFFFFFFFF),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 250.dp)
            )

            Spacer(modifier = Modifier.height(13.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .widthIn(min = 183.dp)
                    .heightIn(min = 18.dp)
                    .background(
                        color = Color(0xFF92ADBE),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "Day 4: Naming the Struggle . 7 min",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 14.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF184159),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(13.dp))

            Text(
                text = "When things are uncertain, it's natural to want control and try to figure everything out before moving forward. But this scripture challenges that habit.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                color = Color(0xFFF0F0F0),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(35.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 42.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Continue Today’s Session",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 22.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF335E78),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp)
                .background(
                    color = Color(0x1A335E78),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(3.dp)
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFF335E78),
                        shape = RoundedCornerShape(
                            topStart = 4.dp,
                            bottomStart = 4.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 15.dp, end = 12.dp)
            ) {
                Text(
                    text = "TODAY’S SCRIPTURE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF335E78),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = 149.dp)
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = "“Be still, and know that I am God; I will be exalted among the nations, I will be exalted in the earth.”",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF535353),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Psalm 46:10 (NIV)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF335E78),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(46.dp))

        Text(
            text = "Recommended For You",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            letterSpacing = 0.sp,
            color = Color(0xFF535353),
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 18.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.recommended),
            contentDescription = "Recommended",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(146.dp)
                .height(121.dp)
                .background(
                    color = Color(0xFFD4DDE4),
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(RoundedCornerShape(4.dp))
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .width(146.dp)
                .align(Alignment.Start),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Fear & Anxiety",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 14.sp,
                letterSpacing = 0.sp,
                color = Color(0xFF1E1E1E),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "6 Formation Paths",
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 13.sp,
                letterSpacing = 0.sp,
                color = Color(0xFF828282),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    BottomNavBar()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    LampStandTheme(dynamicColor = false) {
        DashboardScreen()
    }
}




