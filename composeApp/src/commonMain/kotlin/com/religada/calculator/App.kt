package com.religada.calculator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.religada.calculator.ui.screens.calculator.CalculatorScreen

@Composable
fun App() {
    Navigator(CalculatorScreen)
}

//@Composable
//@Preview
//fun App() {
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
//}