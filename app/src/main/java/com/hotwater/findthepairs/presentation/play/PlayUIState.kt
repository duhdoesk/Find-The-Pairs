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
        val errorMessage: String
    ): PlayUiState()

    /**
     * When all the data has been successfully loaded and the game is ready to start
     */
    data class Success(
        val theme: Theme,
        val cards: List<PlayCard>,
        val flippingState: FLippingState,
        val playState: PlayState
    ): PlayUiState()
}


data class PlayCard(
    val character: Character,
    val cardState: CardState
)


enum class CardState {
    HIDDEN, FLIPPED, FOUND
}

sealed class FLippingState {
    object None: FLippingState()
    data class Single(val single: Int): FLippingState()
    data class Pair(val pair: kotlin.Pair<Int, Int>): FLippingState()
}

enum class PlayState {
    PAUSED, PLAYING, FINISHED
}