package com.hotwater.findthepairs.presentation.play

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayScreen(playViewModel: PlayViewModel = PlayViewModel()) {

    Column(Modifier.wrapContentSize()) {
        Button(onClick = { playViewModel.rawTesting() }) {
            Text(text = "Click Me")
        }
    }
}