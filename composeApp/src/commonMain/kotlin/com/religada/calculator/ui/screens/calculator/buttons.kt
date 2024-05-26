package com.religada.calculator.ui.screens.calculator

val buttons = listOf(
    CalculatorButton("C", ButtonType.CLEAR),
    CalculatorButton("⌫", ButtonType.CLEAR),
    CalculatorButton("( )", ButtonType.HIGHLIGHT),
    CalculatorButton("÷", ButtonType.HIGHLIGHT),
    CalculatorButton("7", ButtonType.DEFAULT),
    CalculatorButton("8", ButtonType.DEFAULT),
    CalculatorButton("9", ButtonType.DEFAULT),
    CalculatorButton("x", ButtonType.HIGHLIGHT),
    CalculatorButton("4", ButtonType.DEFAULT),
    CalculatorButton("5", ButtonType.DEFAULT),
    CalculatorButton("6", ButtonType.DEFAULT),
    CalculatorButton("-", ButtonType.HIGHLIGHT),
    CalculatorButton("1", ButtonType.DEFAULT),
    CalculatorButton("2", ButtonType.DEFAULT),
    CalculatorButton("3", ButtonType.DEFAULT),
    CalculatorButton("+", ButtonType.HIGHLIGHT),
    CalculatorButton("☰", ButtonType.DEFAULT),
    CalculatorButton("0", ButtonType.DEFAULT),
    CalculatorButton(".", ButtonType.DEFAULT),
    CalculatorButton("=", ButtonType.EQUAL)
)
