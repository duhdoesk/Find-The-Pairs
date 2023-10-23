package com.hotwater.findthepairs.presentation.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hotwater.findthepairs.R
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
        is PlayUiState.Success -> PlayScreenSuccess(uiState)
    }
}

@Composable
fun PlayScreenSuccess(uiState: PlayUiState.Success) {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitPlayScreenSuccess(uiState = uiState)
        else -> LandscapePlayScreenSuccess(uiState = uiState)
    }
}

@Composable
fun PortraitPlayScreenSuccess(uiState: PlayUiState.Success) {
    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Expanded -> {
            PortraitExpandedPlayScreenSuccess(uiState)
        }

        else -> {
            PortraitCompactPlayScreenSuccess(uiState)
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