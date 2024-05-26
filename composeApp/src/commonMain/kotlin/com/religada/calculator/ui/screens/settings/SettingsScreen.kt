package com.religada.calculator.ui.screens.settings

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
fun Settings(
    vm: SettingsViewModel,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSettingsClick
        ) {
            Text(text = "Go to Calculator")
        }
    }
}
