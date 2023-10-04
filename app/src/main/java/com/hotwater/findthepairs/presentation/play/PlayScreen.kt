package com.hotwater.findthepairs.presentation.play

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.hotwater.findthepairs.presentation.util.ErrorScreen
import com.hotwater.findthepairs.presentation.util.LoadingScreen

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

}