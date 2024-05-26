package com.religada.calculator.ui.screens.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.tryParseToEnd
import com.github.h0tk3y.betterParse.parser.ParseResult
import com.github.h0tk3y.betterParse.parser.Parsed
import com.religada.calculator.data.MathGrammar

class CalculatorViewModel : ScreenModel {

    var state by mutableStateOf(UiState())
        private set

    private val operations = listOf("+", "-", "x", "÷", "( )", "(", ")") // TODO use variables as symbols, for example: val plus = "+"

    private fun onSettingsClick() {
        state = state.copy(goToSettings = true) // TODO implement unidirectional flow
    }

    fun symbolPressed(symbol: String) {
        when (symbol) {
            "C" -> {
                clear()
            }

            "⌫" -> {
                dropLast()
            }

            "=" -> {
                result()
            }

            "☰" -> {
                onSettingsClick()
            }

            in operations -> {
                addOperation(symbol)
            }

            else -> {
                addNumber(symbol)
            }
        }
        if(symbol != "=") {
            resetResultIsVisible()
        }
    }

    private fun clear() {
        state = state.copy(operation = "")
    }

    private fun dropLast() {
        state = state.copy(operation = state.operation.dropLast(1))
    }

    private fun addOperation(symbol: String) {
        if(state.operation.isEmpty()) return

        if (symbol == "( )") {
            val operation = state.operation
            val openParens = operation.count { it == '(' }
            val closeParens = operation.count { it == ')' }
            state = if (openParens == closeParens) {
                if(state.operation.last().isDigit()) {
                    state.copy(operation = "${operation}x(")
                } else {
                    state.copy(operation = "$operation(")
                }
            } else {
                state.copy(operation = "$operation)")
            }
        } else {
            val lastTerm = state.operation.last().toString()
            state = if (lastTerm in operations) {
                state.copy(operation = state.operation.dropLast(1) + symbol)
            } else {
                state.copy(operation = state.operation + symbol)
            }
        }
    }

    private fun addNumber(number: String) {
        state = if(state.isResultVisible) {
            state.copy(operation = number)
        } else {
            state.copy(operation = state.operation + number)
        }
    }

    private fun toggleSign() {
        // TODO Lock access to uiState until the sign change is complete.
        val currentOperation = state.operation
        state = if (state.operation.startsWith("-(")) {
            state.copy(
                operation = currentOperation.substring(2, currentOperation.length - 1)
            )
        } else {
            state.copy(
                operation = "-($currentOperation)"
            )
        }
        result()
    }

    private fun result() {
        if(state.operation.isEmpty()) return

        // TODO Lock access to uiState until the sign change is complete.
        state = try {
            val evaluatedResult = evaluateExpression(state.operation).formattedToString()
            state.copy(operation = evaluatedResult, result = "", isResultVisible = true)
        } catch (e: Exception) {
            println("__dev: Error: ${e.message}")
            state.copy(result = "Error")
        }
    }

    private fun Double.formattedToString(): String {
        return if (this % 1.0 == 0.0) {
            this.toInt().toString()
        } else {
            this.toString()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val grammar = MathGrammar()
        return try {
            grammar.parseToEnd(expression)
        } catch (e: Exception) {
            println("__dev: Error: ${e.message}")
            throw e
        }
    }

    private fun resetResultIsVisible() {
        state = state.copy(isResultVisible = false)
    }

    fun resetGoToSettings() {
        state = state.copy(goToSettings = false)
    }

    data class UiState(
        val operation: String = "",
        val result: String = "",
        val isResultVisible: Boolean = false,
        val goToSettings: Boolean = false
    )
}
