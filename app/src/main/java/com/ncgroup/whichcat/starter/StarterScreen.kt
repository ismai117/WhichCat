package com.ncgroup.whichcat.starter

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgroup.whichcat.R

private typealias navigateToMainScreen = () -> Unit

@Composable
fun StarterScreen(
    modifier: Modifier = Modifier,
    navigateToMainScreen: navigateToMainScreen
){
    
    Box(
        modifier = modifier.fillMaxSize()
    ){
        
        Column(
            modifier = modifier
                .padding(top = 140.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            Box(
                modifier = modifier
                    .width(288.dp)
                    .height(283.dp),
//                    .border(width = 1.dp, color = Color.White),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.starter_bg),
                    contentDescription = "starter cat background image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxSize()
                )
            }

            Text(
                text = "Let's increase\nyour knowledge!",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Learn more about different\ntypes of cat breeds",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Button(
                onClick = {
                    navigateToMainScreen()
                    setUserState(true)
                },
                modifier = modifier
                    .width(239.dp)
                    .height(52.dp)
            ) {
                Text(text = "Get Started")
            }

        }
        
    }
    
}