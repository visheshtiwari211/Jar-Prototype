package com.example.jarprototype.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.jarprototype.ui.theme.screens.LandingPageScreen

const val landingPageRoute = "landing_page_navigation"

fun NavController.navigateToLandingPage(navOptions: NavOptions? = null) {
    this.navigate(route = landingPageRoute, navOptions = navOptions)
}

fun NavGraphBuilder.LandingPageSection(onBackClick: () -> Unit) {
    composable(route = landingPageRoute) {
        LandingPageScreen(onBackClick = onBackClick)
    }
}