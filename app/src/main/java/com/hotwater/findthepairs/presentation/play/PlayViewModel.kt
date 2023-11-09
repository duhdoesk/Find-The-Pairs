package com.hotwater.findthepairs.presentation.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotwater.findthepairs.presentation.play.component.PlayCard
import com.hotwater.findthepairs.presentation.util.getRawListOfCharacters
import com.hotwater.findthepairs.presentation.util.getRawTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

const val PVM_TAG = "PlayViewModel Log"

class PlayViewModel : ViewModel() {

    private val cards = MutableStateFlow<List<PlayCard>>(emptyList())
    private val flippingState = MutableStateFlow<FlippingState>(FlippingState.NotFlipped)
    private val gameState = MutableStateFlow<GameState>(GameState.PLAYING)

    private val playUiState = combine(cards, flippingState, gameState) { cards, flippingState, gameState ->
        if (cards.isNotEmpty()) {
            PlayUiState.Success(
                theme = getRawTheme(),
                cards = cards,
                flippingState = flippingState,
                gameState = gameState
            )
        }
        else {
            PlayUiState.Loading
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    init {
        val characters = getRawListOfCharacters(16)
        val playCards = characters.map { character ->
            PlayCard(
                character = character,
                cardState = CardState.NOT_FOUND
            )
        }
        cards.update { playCards }
    }


    /**
     * function to be called when the user flips a card.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    fun flipCard(index: Int) {
        when (val state = flippingState.value) {
            is FlippingState.Flipped -> {
                flippingState.update {
                    FlippingState.Flipped(
                        pair = Pair(state.pair.first, index)
                    )
                }
            }

            is FlippingState.NotFlipped -> {
                flippingState.update {
                    FlippingState.Flipped(
                        pair = Pair(index, null)
                    )
                }
            }
        }
    }


    /**
     * function to be called when the user already has two cards flipped.
     * then, we have to check if them both are the same character.
     * if yes, we add this character to the foundPairs list.
     * else, we just flip the cards back.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    private fun checkPair() {
        when (val state = flippingState.value) {

            is FlippingState.Flipped -> {
                val first = cards.value[state.pair.first!!].character
                val second = cards.value[state.pair.second!!].character

                if (first == second) {
                    val cardsUpdated = cards.value.map { playCard ->
                        if (playCard.character == first) {
                            playCard.copy(cardState = CardState.FOUND)
                        } else {
                            playCard
                        }
                    }

                    cards.update {
                        cardsUpdated
                    }
                }
            }

            else -> return
        }
    }
}