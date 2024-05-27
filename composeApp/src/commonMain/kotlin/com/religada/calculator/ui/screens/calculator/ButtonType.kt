package com.religada.calculator.ui.screens.calculator

import androidx.compose.ui.graphics.Color
import com.religada.calculator.ui.theme.gold
import com.religada.calculator.ui.theme.green
import com.religada.calculator.ui.theme.red

enum class ButtonType(val background : Color, val text : Color) {
    DEFAULT (Color.LightGray, Color.Black),
    HIGHLIGHT (gold, Color.White),
    DELETE (red, Color.White),
    RESULT (green, Color.White)
}