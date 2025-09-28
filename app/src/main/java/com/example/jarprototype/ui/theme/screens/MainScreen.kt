package com.example.jarprototype.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToOnBoardingScreen: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToOnBoardingScreen()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF713A65)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to ",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(700),
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = Color(0xFFEEEBF5),
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        Text(
            text = "Onboarding",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                lineHeight = 32.sp,
                color = Color(0xFFF8DC83),
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}