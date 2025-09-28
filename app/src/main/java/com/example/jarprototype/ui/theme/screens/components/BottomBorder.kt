package com.example.jarprototype.ui.theme.screens.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.bottomBorder(strokeWidth: Dp, isCoinCard: Boolean = false) = composed(
    factory = {
        val brush = Brush.linearGradient(listOf(Color(323941), Color.White, Color(131819)))
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = Color(0XFFACA1D3).copy(alpha = 0.1f),
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )

        }
    }
)