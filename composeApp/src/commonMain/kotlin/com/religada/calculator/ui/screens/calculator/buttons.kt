package com.religada.calculator.ui.screens.calculator

import com.religada.calculator.data.CLEAR
import com.religada.calculator.data.DIVISION
import com.religada.calculator.data.DOUBLE_PARENTHESIS
import com.religada.calculator.data.DROP_LAST
import com.religada.calculator.data.EQUAL
import com.religada.calculator.data.MENU
import com.religada.calculator.data.MINUS
import com.religada.calculator.data.MULTIPLICATION
import com.religada.calculator.data.PLUS

val buttons = listOf(
    CalculatorButton(CLEAR, ButtonType.DELETE),
    CalculatorButton(DROP_LAST, ButtonType.DELETE),
    CalculatorButton(DOUBLE_PARENTHESIS, ButtonType.HIGHLIGHT),
    CalculatorButton(DIVISION, ButtonType.HIGHLIGHT),
    CalculatorButton("7", ButtonType.DEFAULT),
    CalculatorButton("8", ButtonType.DEFAULT),
    CalculatorButton("9", ButtonType.DEFAULT),
    CalculatorButton(MULTIPLICATION, ButtonType.HIGHLIGHT),
    CalculatorButton("4", ButtonType.DEFAULT),
    CalculatorButton("5", ButtonType.DEFAULT),
    CalculatorButton("6", ButtonType.DEFAULT),
    CalculatorButton(MINUS, ButtonType.HIGHLIGHT),
    CalculatorButton("1", ButtonType.DEFAULT),
    CalculatorButton("2", ButtonType.DEFAULT),
    CalculatorButton("3", ButtonType.DEFAULT),
    CalculatorButton(PLUS, ButtonType.HIGHLIGHT),
    CalculatorButton(MENU, ButtonType.DEFAULT),
    CalculatorButton("0", ButtonType.DEFAULT),
    CalculatorButton(".", ButtonType.DEFAULT),
    CalculatorButton(EQUAL, ButtonType.RESULT)
)
