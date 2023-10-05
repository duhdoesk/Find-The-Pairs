package com.hotwater.findthepairs.presentation.play

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hotwater.findthepairs.presentation.play.component.PlayCard
import com.hotwater.findthepairs.presentation.util.DeviceOrientation
import com.hotwater.findthepairs.presentation.util.WindowInfo
import com.hotwater.findthepairs.presentation.util.screens.ErrorScreen
import com.hotwater.findthepairs.presentation.util.screens.LoadingScreen
import com.hotwater.findthepairs.presentation.util.rememberDeviceOrientation
import com.hotwater.findthepairs.presentation.util.rememberWindowInfo
import com.hotwater.findthepairs.presentation.util.screens.RotateDeviceScreen

@Composable
fun PlayScreen(playViewModel: PlayViewModel = PlayViewModel()) {

    when (val uiState = playViewModel.playUiState.collectAsState().value) {
        is PlayUiState.Loading -> LoadingScreen()
        is PlayUiState.Error -> ErrorScreen()
        is PlayUiState.Success -> PlayScreenSuccess(uiState)
    }
}

@Composable
fun PlayScreenSuccess(uiState: PlayUiState.Success) {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PlayScreenPortrait(uiState = uiState)
        else -> PlayScreenLandscape(uiState = uiState)
    }
}

@Composable
fun PlayScreenPortrait(uiState: PlayUiState.Success) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Expanded -> {
            RotateDeviceScreen()
        }
        else -> {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 60.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        for (card in uiState.allPairs) {
                            item {
                                Row {
                                    PlayCard(character = card, turned = false)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlayScreenLandscape(uiState: PlayUiState.Success) {

    when (rememberWindowInfo().screenWidthInfo) {
        !is WindowInfo.WindowType.Expanded -> {
            RotateDeviceScreen()
        }
        else -> {

        }
    }
}