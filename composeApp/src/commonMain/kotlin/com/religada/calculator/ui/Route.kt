package com.religada.calculator.ui

sealed interface Route {
    data object Calculator : Route
    data object Settings : Route
}
