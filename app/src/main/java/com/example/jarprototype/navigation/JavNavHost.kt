package com.example.jarprototype.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun JarNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = mainScreenRoute,
        modifier = modifier,
    ) {
        MainScreenSection(
            nestedScreens = {
                OnBoardingScreenSection(
                    onBackClick = { navController.popBackStack() },
                    onNavigateToLandingPage = { navController.navigateToLandingPage() }
                )
                LandingPageSection(onBackClick = { navController.popBackStack() })
            },
            onNavigateToOnBoardingScreen = {
                navController.navigateToOnBoardingScreen()
            }
        )
    }
}