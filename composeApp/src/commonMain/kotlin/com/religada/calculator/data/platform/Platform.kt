package com.religada.calculator.data.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform