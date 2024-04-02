package com.ncgroup.whichcat.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ncgroup.whichcat.starter.getUserState

private typealias navigateToStarterScreen = () -> Unit
private typealias navigateToMainScreen = () -> Unit

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToStarterScreen: navigateToStarterScreen,
    navigateToMainScreen: navigateToMainScreen
){
    
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url("https://lottie.host/60a87560-0483-43a5-94e4-719ccc7ae08d/56d1VCenuK.json")
    )

    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = 2
    )
    
    LaunchedEffect(
        animationState.progress
    ){
        if (animationState.progress == 1f){
            if (getUserState()){
                navigateToMainScreen()
            } else {
                navigateToStarterScreen()
            }
        }
    }
    
    Box(
        modifier = modifier.fillMaxSize()
    ){

        Column(
            modifier = modifier
                .padding(top = 180.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(60.dp)
        ) {

            Text(
                text = "Which Cat?",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            )

            LottieAnimation(
                composition = composition,
                progress = { animationState.progress }
            )

        }
        
    }
    
}