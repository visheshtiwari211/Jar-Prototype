package com.example.jarprototype.utils

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

object Utils {
    fun String.toComposeColor(): Color {
        return Color(this.toColorInt())
    }
}