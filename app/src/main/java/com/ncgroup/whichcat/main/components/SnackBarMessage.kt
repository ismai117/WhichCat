package com.ncgroup.whichcat.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SnackBarMessage(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState
){
    Box(
        modifier = modifier.fillMaxSize()
    ){
        SnackbarHost(
            hostState = hostState,
            modifier = modifier.align(Alignment.TopCenter)
        ){ data ->
            Card(
                modifier = modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Snackbar(
                    action = {}
                ) {
                    Text(
                        text = data.visuals.message
                    )
                }
            }
        }
    }
}