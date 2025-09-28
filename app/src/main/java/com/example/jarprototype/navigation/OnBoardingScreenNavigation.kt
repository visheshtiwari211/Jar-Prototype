package com.example.jarprototype.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.jarprototype.ui.theme.screens.OnBoardingScreen

const val onBoardingScreenRoute = "on_boarding_screen_route"

fun NavController.navigateToOnBoardingScreen(navOptions: NavOptions? = null) {
    this.navigate(route = onBoardingScreenRoute, navOptions = navOptions)
}

fun NavGraphBuilder.OnBoardingScreenSection(
    onNavigateToLandingPage: () -> Unit,
    onBackClick: () -> Unit
) {
    composable(route = onBoardingScreenRoute) {
        OnBoardingScreen(
            onNavigateToLandingPage = onNavigateToLandingPage,
            onBackClick = onBackClick
        )
    }
}