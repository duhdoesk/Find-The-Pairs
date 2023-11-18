package com.hotwater.findthepairs.presentation.play

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.presentation.util.getRawListOfCharacters
import com.hotwater.findthepairs.presentation.util.getRawTheme
import com.hotwater.findthepairs.presentation.util.rawTesting
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val PVM_TAG = "PlayViewModel Log"

class PlayViewModel : ViewModel() {

    private val cards = MutableStateFlow<List<PlayCard>>(emptyList())
    private val gameState = MutableStateFlow(GameState.PLAYING)

    val playUiState = combine(cards, gameState) { cards, gameState ->
        if (cards.isNotEmpty()) {
            PlayUiState.Success(
                theme = getRawTheme(),
                cards = cards,
                gameState = gameState
            )
        } else {
            PlayUiState.Error(
                errorMessage = "We could not load the data. Try again.",
                retry = { getPlayCardsData() })
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PlayUiState.Loading)

    init {
        getPlayCardsData()
    }

    private fun getPlayCardsData() {
        cards.update { rawTesting() }
    }


    /**
     * function to be called when the user flips a card.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    fun flipCard(card: PlayCard) {
        val cardsUpdated = cards.value.map { playCard ->
            if (playCard.cardId == card.cardId) {
                playCard.copy(cardState = playCard.cardState.next)
            } else playCard
        }

        cards.update { cardsUpdated }
        viewModelScope.launch {
            checkFlipped()
        }
    }


    /**
     * function to be called when the user already has two cards flipped.
     * then, we have to check if them both are the same character.
     * if yes, we update their CardState property to FOUND.
     * otherwise, we update it to HIDDEN again.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    private suspend fun checkFlipped() {

        val flippedCards = cards.value.filter { it.cardState == CardState.FaceUp }

        if (flippedCards.size < 2) return
        else if (flippedCards.size > 2) {
            cards.update { cards ->
                cards.map { playCard ->
                    if (playCard.cardState == CardState.FaceUp) {
                        playCard.copy(cardState = playCard.cardState.next)
                    } else {
                        playCard
                    }
                }
            }
        }

        delay(1400)

        val (first, second) = flippedCards
        if (first.character.characterId == second.character.characterId) {
            cards.update { cards ->
                cards.map {
                    if (it.cardId == first.cardId || it.cardId == second.cardId) {
                        it.copy(cardState = CardState.Found)
                    } else {
                        it
                    }
                }
            }
        } else {
            cards.update { cards ->
                cards.map { playCard ->
                    if (playCard.cardState == CardState.FaceUp) {
                        playCard.copy(cardState = playCard.cardState.next)
                    } else {
                        playCard
                    }
                }
            }
        }
    }
}