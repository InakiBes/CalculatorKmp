package com.religada.calculator.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class SettingsViewModel : ScreenModel {

    var state by mutableStateOf(UiState())
        private set


    data class UiState(
        val result: Int = 0
    )
}