package com.hotwater.findthepairs.presentation.play

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hotwater.findthepairs.R
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

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f)
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
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

                    Row(Modifier.fillMaxWidth()) {
                        Text(text = "Pares Encontrados: ${uiState.foundPairs.size / 2} / ${uiState.allPairs.size / 2}")
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "02:47",
                            fontSize = 28.sp
                        )
                        TextButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_pause_24), contentDescription = "Pause Icon")
                            Text(text = "Pausa")
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