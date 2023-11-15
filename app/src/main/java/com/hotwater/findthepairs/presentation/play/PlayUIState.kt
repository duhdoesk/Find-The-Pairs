package com.hotwater.findthepairs.presentation.play

import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.domain.model.Theme

sealed class PlayUiState {

    /**
     * When some work is being done to get all the data available and ready
     */
    object Loading: PlayUiState()

    /**
     * When there has been an error while loading data and/or setting all stuff up
     */
    data class Error(
        val errorMessage: String,
        val retry: () -> Unit
    ): PlayUiState()

    /**
     * When all the data has been successfully loaded and the game is ready to start
     */
    data class Success(
        val theme: Theme,
        val cards: List<PlayCard>,
        val gameState: GameState
    ): PlayUiState()
}


data class PlayCard(
    val cardId: Int,
    val character: Character,
    var cardState: CardState
)

enum class CardState {
    HIDDEN, FLIPPED, FOUND
}

enum class GameState {
    PAUSED, PLAYING, FINISHED
}