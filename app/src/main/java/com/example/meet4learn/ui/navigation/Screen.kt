package com.example.meet4learn.ui.navigation

sealed class Screen( val route: String) {
    object Login : Screen("login")
    object Register: Screen("register")
    object Dashboard : Screen("dashboard")
    object Splash: Screen("splash")
}