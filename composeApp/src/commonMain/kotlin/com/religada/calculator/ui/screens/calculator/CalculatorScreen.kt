package com.religada.calculator.ui.screens.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.religada.calculator.ui.theme.lightGrayBackground
import com.religada.calculator.ui.screens.settings.SettingsScreen

object CalculatorScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Calculator(
            vm = rememberScreenModel { CalculatorViewModel() },
            onSettingsClick = { navigator.push(SettingsScreen) }
        )
    }
}

@Composable
fun Calculator(
    vm: CalculatorViewModel,
    onSettingsClick: () -> Unit
) {
    LaunchedEffect(vm.state.goToSettings) {
        if (vm.state.goToSettings) {
            onSettingsClick()
            vm.resetGoToSettings()
        }
    }

    MaterialTheme {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(lightGrayBackground)
                    .padding(padding)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                CalculatorDisplay(
                    operation = vm.state.operation,
                    result = vm.state.result,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 200.dp)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                CalculatorKeyboard {
                    vm.symbolPressed(it)
                }
            }
        }
    }
}

@Composable
fun CalculatorDisplay(operation: String = "", result: String = "", modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = operation,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 60.sp
            )
            Text(
                text = result,
                fontSize = 24.sp,
                color = Color.Gray,
                lineHeight = 32.sp
            )
        }
    }
}

@Composable
fun CalculatorKeyboard(onButtonClick: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        buttons.chunked(4).forEachIndexed { index, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (button in row) {
                    CalculatorButton(
                        symbol = button.symbol,
                        type = button.type,
                        onClick = { onButtonClick(button.symbol) }
                    )
                }
            }
            if (index < buttons.chunked(4).size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CalculatorButton(symbol: String, type: ButtonType, onClick: () -> Unit) {
    val (bgColor, textColor) = (type.background to type.text)
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color = bgColor, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
