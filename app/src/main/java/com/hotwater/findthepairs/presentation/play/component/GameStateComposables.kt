package com.hotwater.findthepairs.presentation.play.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hotwater.findthepairs.R
import com.hotwater.findthepairs.presentation.play.GameState

@Composable
fun GameStateButton(
    gameState: GameState,
    onClick: () -> Unit
) {
    when (gameState) {
        GameState.PLAYING -> {
            TextButton(onClick = { onClick() }) {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause_24),
                    contentDescription = "Pause Icon"
                )
                Text(text = "Pausa")
            }
        }

        else -> {
            TextButton(onClick = { onClick() }) {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = "resume Icon"
                )
                Text(text = "Resume")
            }
        }
    }
}

@Composable
fun GamePausedBanner() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Red)
    ) {
        Text(
            text = "Jogo Pausado",
            color = Color.White
        )
    }
}