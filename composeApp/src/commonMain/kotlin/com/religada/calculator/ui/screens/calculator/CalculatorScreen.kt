package com.religada.calculator.ui.screens.calculator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.religada.calculator.ui.screens.settings.SettingsScreen

object CalculatorScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Calculator(
            vm = rememberScreenModel { CalculatorViewModel() },
            onButtonClick = { navigator.push(SettingsScreen) }
        )
    }
}

@Composable
expect fun Calculator(vm: CalculatorViewModel, onButtonClick: () -> Unit)