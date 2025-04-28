package com.example.testtaskwallee.presentation.navigation

sealed class NavigationRoutes(val route: String) {
    object PinPad: NavigationRoutes("pinpad_screen")
    object Receipt: NavigationRoutes("receipt_screen")
}