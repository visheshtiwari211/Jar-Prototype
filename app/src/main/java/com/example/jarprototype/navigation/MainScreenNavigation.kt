package com.example.jarprototype.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.jarprototype.ui.theme.screens.MainScreen

const val mainScreenRoute = "main_screen_route"

fun NavController.navigateToMainScreenRoute(navOptions: NavOptions) {
    this.navigate(mainScreenRoute, navOptions)
}

fun NavGraphBuilder.MainScreenSection(onNavigateToOnBoardingScreen:() -> Unit, nestedScreens: NavGraphBuilder.() -> Unit) {
    composable(route = mainScreenRoute) {
        MainScreen(onNavigateToOnBoardingScreen = onNavigateToOnBoardingScreen )
    }
    nestedScreens()
}