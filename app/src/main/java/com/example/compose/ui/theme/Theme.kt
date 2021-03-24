package com.example.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(state: MutableState<Int>, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = when (state.value) {
            0 -> {
                FirstLightThemeColors
            }
            1 -> {
                SecondLightThemeColors
            }
            else -> {
                ThirdLightThemeColors
            }
        },
        content = content
    )
}

private val FirstLightThemeColors = lightColors(
    primary = primaryRed,
    primaryVariant = purple700,
    onPrimary = Color.White,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = Color.White
)

private val SecondLightThemeColors = lightColors(
    primary = primaryBlue,
    primaryVariant = purple700,
    onPrimary = Color.White,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = Color.White
)

private val ThirdLightThemeColors = lightColors(
    primary = Color.Green,
    primaryVariant = purple700,
    onPrimary = Color.White,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = Color.White
)
