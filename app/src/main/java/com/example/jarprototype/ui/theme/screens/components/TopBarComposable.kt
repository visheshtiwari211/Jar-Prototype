package com.example.jarprototype.ui.theme.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Icon
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

@Composable
fun TopBarComposable(topBarText: String, modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 15.5.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(height = 24.dp, width = 20.dp)
                    .padding(vertical = 2.dp)
                    .clickable { onBackClick() },
                tint = Color.White
            )

            Text(
                text = topBarText,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = Color.White,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    }
}