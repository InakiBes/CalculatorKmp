package com.religada.calculator.ui.screens.settings

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Settings(
            vm = rememberScreenModel { SettingsViewModel() },
            onSettingsClick = { navigator.pop() }
        )
    }
}

@Composable
expect fun Settings(vm: SettingsViewModel, onSettingsClick: () -> Unit)

