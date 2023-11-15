package com.hotwater.findthepairs.presentation.play

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hotwater.findthepairs.presentation.play.screens.LandscapeCompactPlayScreenSuccess
import com.hotwater.findthepairs.presentation.play.screens.LandscapeExpandedPlayScreenSuccess
import com.hotwater.findthepairs.presentation.play.screens.PortraitCompactPlayScreenSuccess
import com.hotwater.findthepairs.presentation.play.screens.PortraitExpandedPlayScreenSuccess
import com.hotwater.findthepairs.presentation.util.DeviceOrientation
import com.hotwater.findthepairs.presentation.util.WindowInfo
import com.hotwater.findthepairs.presentation.util.rememberDeviceOrientation
import com.hotwater.findthepairs.presentation.util.rememberWindowInfo
import com.hotwater.findthepairs.presentation.util.screens.ErrorScreen
import com.hotwater.findthepairs.presentation.util.screens.LoadingScreen

@Composable
fun PlayScreen(playViewModel: PlayViewModel = PlayViewModel()) {

    val uiState: PlayUiState by playViewModel.playUiState.collectAsState()

    when (uiState) {
        is PlayUiState.Loading -> LoadingScreen()
        is PlayUiState.Error -> ErrorScreen()

        is PlayUiState.Success -> PlayScreenSuccess(
            uiState = uiState as PlayUiState.Success,
            playViewModel = playViewModel
        )
    }
}

@Composable
fun PlayScreenSuccess(
    uiState: PlayUiState.Success,
    playViewModel: PlayViewModel
) {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitPlayScreenSuccess(
            uiState = uiState,
            onTurn = {
                Log.d(
                    "PlayScreenSuccess",
                    "onTurn $it"
                )
                playViewModel.flipCard(it)
            }
        )

        else -> LandscapePlayScreenSuccess(uiState = uiState)
    }
}

@Composable
fun PortraitPlayScreenSuccess(
    uiState: PlayUiState.Success,
    onTurn: (card: PlayCard) -> Unit
) {
    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Expanded -> {
            PortraitExpandedPlayScreenSuccess(uiState)
        }

        else -> {
            PortraitCompactPlayScreenSuccess(
                uiState = uiState,
                onTurn = {
                    Log.d(
                        "PortraitPlayScreenSuccess",
                        "onTurn $it"
                    )
                    onTurn(it)
                }
            )
        }
    }
}

@Composable
fun LandscapePlayScreenSuccess(uiState: PlayUiState.Success) {
    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Expanded -> {
            LandscapeExpandedPlayScreenSuccess(uiState)
        }

        else -> {
            LandscapeCompactPlayScreenSuccess(uiState)
        }
    }
}