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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.lampstand.R
import com.example.lampstand.ui.theme.LampStandTheme

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
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
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    LampStandTheme {
        DashboardScreen()
    }
}




