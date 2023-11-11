package com.hotwater.findthepairs.presentation.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotwater.findthepairs.domain.model.Character
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

    private val flipped = MutableStateFlow(
        cards.value.filter { it.cardState == CardState.FLIPPED })

    private val found = MutableStateFlow(
        cards.value.filter { it.cardState == CardState.FOUND })

    private val gameState = MutableStateFlow<GameState>(GameState.PLAYING)

    val playUiState = combine(cards, flipped, found, gameState) { cards, flipped, found, gameState ->
        if (cards.isNotEmpty()) {
            PlayUiState.Success(
                theme = getRawTheme(),
                cards = cards,
                flipped = flipped,
                found = found,
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
        val characters = getRawListOfCharacters(16)
        val playCards = characters.map { character ->
            PlayCard(
                character = character,
                cardState = CardState.HIDDEN
            )
        }
        cards.update { playCards }
    }


    /**
     * function to be called when the user flips a card.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    fun flipCard(card: PlayCard) {
        val cardsUpdated = cards.value.map { playCard ->
            if (playCard == card) {
                PlayCard(character = card.character, cardState = CardState.FLIPPED)
            }

            else playCard
        }

        cards.update { cardsUpdated }
        checkFlipped()
    }


    /**
     * function to be called when the user already has two cards flipped.
     * then, we have to check if them both are the same character.
     * if yes, we update their CardState property to FOUND.
     * otherwise, we update it to HIDDEN again.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    private fun checkFlipped() {
        val cardsUpdated: List<PlayCard>

        /**
         * first, we check if we got exactly 2 flipped cards.
         */
        if (flipped.value.size == 2) {

            /**
             * if the both flipped cards are the same
             */
            if (flipped.value.first().character == flipped.value.last().character) {
                cardsUpdated = cards.value.map { playCard ->
                    if (playCard.character == flipped.value.first().character) {
                        PlayCard(character = playCard.character, cardState = CardState.FOUND)
                    }

                    else playCard
                }
            }

            /**
             * if they are different
             */
            else {
                cardsUpdated = cards.value.map { playCard ->
                    if (playCard.character == flipped.value.first().character) {
                        PlayCard(character = playCard.character, cardState = CardState.HIDDEN)
                    }

                    else playCard
                }
            }

            cards.update { cardsUpdated }
        }

        /**
         * if somehow the user managed to flip more than 2 cards, we must just make them
         * hidden again
         */
        else if (flipped.value.size > 2) {
            cardsUpdated = cards.value.map { playCard ->
                if (playCard.character == flipped.value.first().character) {
                    PlayCard(character = playCard.character, cardState = CardState.HIDDEN)
                }

                else playCard
            }
        }
    }
}