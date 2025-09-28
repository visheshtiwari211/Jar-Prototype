package com.example.jarprototype.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jarprototype.ui.theme.screens.components.TopBarComposable
import com.example.jarprototype.ui.theme.screens.components.bottomBorder

@Composable
fun LandingPageScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF201929)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TopBarComposable(
            topBarText = "Landing Page",
            onBackClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .bottomBorder(strokeWidth = 1.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Landing page ",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 24.sp,
                    lineHeight = 100.sp,
                    letterSpacing = 0.sp,
                    color = Color.White,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    }
}