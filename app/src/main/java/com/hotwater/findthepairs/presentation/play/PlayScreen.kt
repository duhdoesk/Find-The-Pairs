package com.hotwater.findthepairs.presentation.play

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hotwater.findthepairs.R
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.presentation.play.component.PlayCard
import com.hotwater.findthepairs.presentation.play.screens.LandscapeCompactPlayScreenSuccess
import com.hotwater.findthepairs.presentation.play.screens.LandscapeExpandedPlayScreenSuccess
import com.hotwater.findthepairs.presentation.play.screens.PortraitCompactPlayScreenSuccess
import com.hotwater.findthepairs.presentation.play.screens.PortraitExpandedPlayScreenSuccess
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

        is PlayUiState.Success -> PlayScreenSuccess(
            uiState = uiState,
            onTurn = {
                Log.d(
                    "PlayScreenSuccess",
                    "onTurn $it"
                )
                playViewModel.flipCard(it)
            }
        )
    }
}

@Composable
fun PlayScreenSuccess(
    uiState: PlayUiState.Success,
    onTurn: (index: Int) -> Unit
) {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitPlayScreenSuccess(
            uiState = uiState,
            onTurn = {
                Log.d(
                    "PlayScreenSuccess",
                    "onTurn $it"
                )
                onTurn(it)
            }
        )

        else -> LandscapePlayScreenSuccess(uiState = uiState)
    }
}

@Composable
fun PortraitPlayScreenSuccess(
    uiState: PlayUiState.Success,
    onTurn: (index: Int) -> Unit
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
                    onTurn(it) }
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