package com.religada.calculator.ui.screens.calculator

import androidx.compose.ui.graphics.Color
import com.religada.calculator.design.gold
import com.religada.calculator.design.green
import com.religada.calculator.design.red

enum class ButtonType(val background : Color, val text : Color) {
    DEFAULT (Color.LightGray, Color.Black),
    HIGHLIGHT (gold, Color.White),
    CLEAR (red, Color.White),
    EQUAL (green, Color.White)
}