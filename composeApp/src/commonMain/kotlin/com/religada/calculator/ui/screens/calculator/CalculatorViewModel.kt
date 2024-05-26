package com.religada.calculator.ui.screens.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class CalculatorViewModel : ScreenModel {

    var state by mutableStateOf(UiState())
        private set

    fun onSettingsClick() {
        state = state.copy(goToSettings = true) // TODO implement unidirectional flow
    }

    fun symbolPressed(symbol: String) {
        when (symbol) {
            "C" -> {
                clear()
            }
            "=" -> {
                result()
            }
            "±" -> {
                toggleSign()
            }
            "+", "-", "*", "/", "(", ")" -> {
                addOperation(symbol)
            }
            else -> {
                addNumber(symbol)
            }
        }
    }

    private fun clear() {
        state = state.copy(operation = "")
    }

    private fun addOperation(symbol: String) {
        state = state.copy(operation = "${state.operation} $symbol ")
    }

    private fun addNumber(symbol: String) {
        state = state.copy(operation = state.operation + symbol)
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
    }

    private fun result() {
        // TODO Lock access to uiState until the sign change is complete.
        try {
            val evaluatedResult = evaluateExpression(state.operation)
            state = state.copy(result = evaluatedResult.toString())
        } catch (e: Exception) {
            state = state.copy(result = "Error")
        }
    }

    private fun evaluateExpression(expression: String): Double {
        // TODO implement mathematical expression evaluation
        return 123456789.0
    }

    data class UiState(
        val operation: String = "",
        val result: String = "0",
        val goToSettings: Boolean = false
    )
}