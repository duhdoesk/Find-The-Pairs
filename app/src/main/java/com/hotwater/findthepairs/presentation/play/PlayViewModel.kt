package com.hotwater.findthepairs.presentation.play

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hotwater.findthepairs.presentation.util.getRawPlayUiStateSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val PVM_TAG = "PlayViewModel Log"

class PlayViewModel : ViewModel() {

    private val _playUiState = MutableStateFlow<PlayUiState>(PlayUiState.Loading)
    val playUiState = _playUiState.asStateFlow()

    init {
        _playUiState.value = getRawPlayUiStateSuccess()
    }


    /**
     * function to be called when the user flips a card.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    fun flipCard(index: Int) {
    }


    /**
     * function to be called when the user already has two cards flipped.
     * then, we have to check if them both are the same character.
     * if yes, we add this character to the foundPairs list.
     * else, we just flip the cards back.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    private fun checkPair() {
    }
}