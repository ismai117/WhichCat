package com.ncgroup.whichcat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ncgroup.whichcat.main.catBreeds.presentation.CatBreedScreen
import com.ncgroup.whichcat.main.cats.domain.model.CatDetail
import com.ncgroup.whichcat.main.cats.presentation.CatDetailScreen
import com.ncgroup.whichcat.main.cats.presentation.CatsScreen


private const val CAT_BREED_SCREEN = "cat_breed_screen"
private const val CATS_SCREEN = "cats_screen"
private const val CAT_DETAIL_SCREEN = "cat_detail_screen"


@Composable
fun MainNavigation(
    navController: NavController
){

    var id by remember { mutableStateOf("") }

    NavHost(
        navController = navController as NavHostController,
        startDestination = CAT_BREED_SCREEN
    ){
        composable(
            route = CAT_BREED_SCREEN
        ){
            CatBreedScreen(
                navigateToCatsScreen = {
                    id = it
                    navController.navigate(CATS_SCREEN)
                }
            )
        }
        composable(
            route = CATS_SCREEN
        ){
            CatsScreen(
                id = id,
                navigateToCatDetailScreen = { catDetail->
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat_detail", catDetail)
                    navController.navigate(CAT_DETAIL_SCREEN)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = CAT_DETAIL_SCREEN
        ){
            val catDetail = navController.previousBackStackEntry?.savedStateHandle?.get<CatDetail>("cat_detail")
            CatDetailScreen(
                catDetail = catDetail,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}