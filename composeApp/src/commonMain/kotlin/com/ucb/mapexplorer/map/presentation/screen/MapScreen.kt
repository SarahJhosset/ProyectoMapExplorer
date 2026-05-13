package com.ucb.mapexplorer.map.presentation.screen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MapScreen() {
    MapViewContainer(
        modifier = Modifier.fillMaxSize()
    )
}