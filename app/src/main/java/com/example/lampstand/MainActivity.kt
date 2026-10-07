package com.example.lampstand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.lampstand.dashboard.DashboardScreen
import com.example.lampstand.paths.PathsScreen
import com.example.lampstand.splash.LoadingScreen
import com.example.lampstand.ui.theme.LampStandTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            LampStandTheme(dynamicColor = false) {
                var showLoading by remember { mutableStateOf(true) }
                LaunchedEffect(Unit) {
                    delay(2000)
                    showLoading = false
                }
                if (showLoading) {
                    LoadingScreen()
                } else {
                    var selectedTab by remember { mutableStateOf(0) }
                    when (selectedTab) {
                        1 -> PathsScreen(
                            onItemSelected = { selectedTab = it }
                        )
                        else -> DashboardScreen(
                            onItemSelected = { selectedTab = it }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainActivityPreview() {
    LampStandTheme(dynamicColor = false) {
        LoadingScreen()
    }
}
