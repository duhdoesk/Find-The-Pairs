package com.hotwater.findthepairs.presentation.play.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hotwater.findthepairs.R
import com.hotwater.findthepairs.presentation.play.PlayUiState
import com.hotwater.findthepairs.presentation.play.component.PlayCard
import com.hotwater.findthepairs.presentation.play.component.PlayCardsLazyVerticalGrid


/**
 * COMPACT AND MEDIUM SCREENS
 */

@Composable
fun PortraitCompactPlayScreenSuccess(uiState: PlayUiState.Success) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
                .sizeIn(maxWidth = 600.dp, maxHeight = 1000.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.Cyan)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {

                PlayCardsLazyVerticalGrid(
                    uiState = uiState,
                    columnsMinSize = 60.dp,
                    cardsSpacing = 6.dp
                )
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
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_pause_24),
                        contentDescription = "Pause Icon"
                    )
                    Text(text = "Pausa")
                }
            }
        }
    }
}


/**
 * EXPANDED SCREENS
 */

@Composable
fun PortraitExpandedPlayScreenSuccess(uiState: PlayUiState.Success) {

}