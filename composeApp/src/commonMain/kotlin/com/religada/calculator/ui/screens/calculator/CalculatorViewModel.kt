package com.religada.calculator.ui.screens.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class CalculatorViewModel : ScreenModel {

    var state by mutableStateOf(UiState())
        private set


    data class UiState(
        val result: Int = 0
    )
}