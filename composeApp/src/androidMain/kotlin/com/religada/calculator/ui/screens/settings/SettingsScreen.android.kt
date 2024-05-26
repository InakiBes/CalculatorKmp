package com.religada.calculator.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
actual fun Settings(
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