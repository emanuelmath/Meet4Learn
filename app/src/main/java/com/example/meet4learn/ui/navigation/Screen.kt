package com.example.meet4learn.ui.navigation

sealed class Screen( val route: String) {
    object Login : Screen("login")
    object Register: Screen("register")
    object Splash: Screen("splash")
    object Main: Screen ("main")
    object Dashboard : Screen("dashboard")
}