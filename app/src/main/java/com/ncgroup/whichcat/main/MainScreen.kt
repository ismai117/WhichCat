package com.ncgroup.whichcat.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ncgroup.whichcat.navigation.MainNavigation
import com.ncgroup.whichcat.navigation.RootNavigation


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
){

    val navController = rememberNavController()

    Scaffold { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            MainNavigation(
                navController = navController
            )
        }
    }
    
}