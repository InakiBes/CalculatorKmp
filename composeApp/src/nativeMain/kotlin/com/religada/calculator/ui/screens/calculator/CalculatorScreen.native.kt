package com.religada.calculator.ui.screens.calculator

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
actual fun Calculator(
    vm: CalculatorViewModel,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Calculator",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClick
        ) {
            Text(text = "Go to Settings")
        }
    }
}