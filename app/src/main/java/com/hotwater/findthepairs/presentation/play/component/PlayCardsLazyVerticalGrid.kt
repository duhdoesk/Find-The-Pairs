package com.hotwater.findthepairs.presentation.play.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hotwater.findthepairs.presentation.play.CardState
import com.hotwater.findthepairs.presentation.play.PlayUiState

@Composable
fun PlayCardsLazyVerticalGrid(
    uiState: PlayUiState.Success,
    columnsMinSize: Dp = 60.dp,
    cardsSpacing: Dp = 6.dp,
    onTurn: (index: Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = columnsMinSize),
        verticalArrangement = Arrangement.spacedBy(cardsSpacing),
        horizontalArrangement = Arrangement.spacedBy(cardsSpacing)
    ) {

        uiState.cards.forEachIndexed { index, card ->
            item {
                val turned = card.state == CardState.FLIPPED

                Row {
                    PlayCard(
                        character = card.character,
                        turned = turned,
                        onTurn = {
                            Log.d(
                                "PlayCardsLazyVerticalGrid",
                                "onTurn $index"
                            )
                            onTurn(index)
                        }
                    )
                }

                Log.d(
                    "LazyVerticalGrid",
                    "$turned"
                )
            }
        }
    }
}