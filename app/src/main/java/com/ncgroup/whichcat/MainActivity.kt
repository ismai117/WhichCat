package com.ncgroup.whichcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ncgroup.whichcat.navigation.RootNavigation
import com.ncgroup.whichcat.ui.theme.WhichCatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhichCatTheme {
                App()
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    Scaffold { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            RootNavigation(
                navController = navController
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhichCatTheme {
       App()
    }
}