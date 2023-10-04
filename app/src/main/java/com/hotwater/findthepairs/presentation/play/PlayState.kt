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
        val foundPairs: List<Character>,
        val notFoundPairs: List<Character>,
        val playingPhase: PlayingPhase
    ): PlayUiState()
}

/**
 * When the UI State is success, the playing phase must be one of those:
 * NOT_STARTED - waiting for the user to start the game;
 * RUNNING - user started the game and is looking for the pairs, but they did not found it all; or
 * OVER - user found all the pairs and won the game.
 */
enum class PlayingPhase {
    NOT_STARTED, RUNNING, OVER
}