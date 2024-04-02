package com.ncgroup.whichcat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ncgroup.whichcat.main.MainScreen
import com.ncgroup.whichcat.splash.SplashScreen
import com.ncgroup.whichcat.starter.StarterScreen

private const val SPLASH = "splash_screen"
private const val STARTER = "starter_screen"
private const val MAIN = "main_screen"

@Composable
fun RootNavigation(
    navController: NavController
){

    NavHost(
        navController = navController as NavHostController,
        startDestination = SPLASH
    ){
        composable(
            route = SPLASH
        ){
            SplashScreen(
                navigateToStarterScreen = {
                    navController.navigate(STARTER)
                },
                navigateToMainScreen = {
                    navController.navigate(MAIN)
                }
            )
        }
        composable(
            route = STARTER
        ){
            StarterScreen(
                navigateToMainScreen = {
                    navController.navigate(MAIN)
                }
            )
        }
        composable(
            route = MAIN
        ){
            MainScreen()
        }
    }

}