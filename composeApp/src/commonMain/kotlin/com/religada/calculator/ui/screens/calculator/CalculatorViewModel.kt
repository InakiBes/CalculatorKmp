package com.religada.calculator.ui.screens.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.religada.calculator.data.CLEAR
import com.religada.calculator.data.DIVISION
import com.religada.calculator.data.DOUBLE_PARENTHESIS
import com.religada.calculator.data.DROP_LAST
import com.religada.calculator.data.EQUAL
import com.religada.calculator.data.ERROR_RESULT
import com.religada.calculator.data.LEFT_PARENTHESIS
import com.religada.calculator.data.MENU
import com.religada.calculator.data.MINUS
import com.religada.calculator.data.MULTIPLICATION
import com.religada.calculator.data.MathGrammar
import com.religada.calculator.data.PLUS
import com.religada.calculator.data.RIGHT_PARENTHESIS

class CalculatorViewModel : ScreenModel {

    var state by mutableStateOf(UiState())
        private set

    private val operations = listOf(
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,
        DOUBLE_PARENTHESIS,
        LEFT_PARENTHESIS,
        RIGHT_PARENTHESIS
    )

    private fun onSettingsClick() {
        state = state.copy(goToSettings = true)
    }

    fun symbolPressed(symbol: String) {
        when (symbol) {
            CLEAR -> {
                clear()
            }

            DROP_LAST -> {
                dropLast()
            }

            EQUAL -> {
                result()
            }

            MENU -> {
                onSettingsClick()
            }

            in operations -> {
                addOperation(symbol)
            }

            else -> {
                addNumber(symbol)
            }
        }
        if (symbol != EQUAL) {
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
        state = if (state.operation.isEmpty()) {
            when (symbol) {
                MINUS -> {
                    state.copy(operation = state.operation + symbol)
                }

                DOUBLE_PARENTHESIS -> {
                    state.copy(operation = state.operation + LEFT_PARENTHESIS)
                }

                else -> state
            }
        } else {
            when (symbol) {
                MINUS ->
                    state.copy(operation = state.operation + symbol)

                DOUBLE_PARENTHESIS -> {
                    val operation = state.operation
                    val openParens = operation.count { it == LEFT_PARENTHESIS.toCharArray()[0] }
                    val closeParens = operation.count { it == RIGHT_PARENTHESIS.toCharArray()[0] }
                    if (openParens == closeParens) {
                        if (state.operation.last().isDigit() || state.operation.last() == RIGHT_PARENTHESIS.toCharArray()[0]){
                            state.copy(operation = "$operation$MULTIPLICATION$LEFT_PARENTHESIS")
                        } else {
                            state.copy(operation = "$operation$LEFT_PARENTHESIS")
                        }
                    } else {
                        state.copy(operation = "$operation$RIGHT_PARENTHESIS")
                    }
                }

                else -> {
                    val lastTerm = state.operation.last().toString()
                    if (lastTerm in operations) {
                        state.copy(operation = state.operation.dropLast(1) + symbol)
                    } else {
                        state.copy(operation = state.operation + symbol)
                    }
                }
            }
        }
    }

    private fun addNumber(number: String) {
        state = state.copy(
            operation = if (state.isResultVisible) number else state.operation + number
        )
    }

    private fun toggleSign() {
        // TODO Lock access to uiState until the sign change is complete.
        val currentOperation = state.operation
        state = if (state.operation.startsWith("$MINUS$LEFT_PARENTHESIS")) {
            state.copy(
                operation = currentOperation.substring(2, currentOperation.length - 1)
            )
        } else {
            state.copy(
                operation = "$MINUS$LEFT_PARENTHESIS$currentOperation$RIGHT_PARENTHESIS"
            )
        }
        result()
    }

    private fun result() {
        if (state.operation.isEmpty()) return

        // TODO Lock access to uiState until the sign change is complete.
        state = try {
            val evaluatedResult = evaluateExpression(state.operation).formattedToString()
            state.copy(operation = evaluatedResult, result = "", isResultVisible = true)
        } catch (e: Exception) {
            println("__dev: Error: ${e.message}")
            state.copy(result = ERROR_RESULT)
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
