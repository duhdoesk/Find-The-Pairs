package com.hotwater.findthepairs.presentation.play

import android.os.Debug
import android.util.Log
import androidx.lifecycle.ViewModel
import com.hotwater.findthepairs.presentation.util.getRawPlayUiStateSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
    fun flipCard(cardIndex: Int) {

        Log.d(
            PVM_TAG,
            "flipCard() called - $playUiState"
        )

        when (val state = playUiState.value) {
            is PlayUiState.Success -> {

                val first = state.flippedCards.first
                val second = state.flippedCards.second

                /**
                 * when no cards are flipped
                 *
                 * in this case, we will update the flippedCards pair, telling that we have a
                 * single card flipped
                 */
                if (first == null) {
                    val flippedCardsUpdated = Pair(cardIndex, null)

                    _playUiState.value = state.copy(
                        flippedCards = flippedCardsUpdated
                    )

                    Log.d(
                        PVM_TAG,
                        "flipCard() first card - $playUiState"
                    )
                }

                /**
                 * when only one card is flipped
                 *
                 * in this case, we will call checkPair() after updating the flippedCards pair,
                 * because we already have two flipped cards
                 */
                else if (second == null) {
                    val flippedCardsUpdated = Pair(first, cardIndex)

                    _playUiState.value = state.copy(
                        flippedCards = flippedCardsUpdated
                    )

                    Log.d(
                        PVM_TAG,
                        "flipCard() second card - $playUiState"
                    )

                    checkPair(flippedCardsUpdated)
                }
            }

            /**
             * when the UI stata is different from Playing.
             * this must not even be possible, in fact.
             */
            else -> return
        }

    }


    /**
     * function to be called when the user already has two cards flipped.
     * then, we have to check if them both are the same character.
     * if yes, we add this character to the foundPairs list.
     * else, we just flip the cards back.
     * the 'flip' move will only be available when the state is PlayUiState.Playing
     */
    private fun checkPair(flippedCards: Pair<Int, Int>) {

        Log.d(
            PVM_TAG,
            "checkPair() called - $playUiState"
        )

        when (val state = playUiState.value) {

            is PlayUiState.Success -> {
                val first = state.allPairs[flippedCards.first]
                val second = state.allPairs[flippedCards.second]

                if (first == second) {
                    val foundPairsUpdated = state.foundPairs.toMutableList()
                    foundPairsUpdated.add(first)

                    _playUiState.value = state.copy(
                        foundPairs = foundPairsUpdated,
                        flippedCards = Pair(null, null)
                    )

                    Log.d(
                        PVM_TAG,
                        "checkPair() same character - $playUiState"
                    )
                }

                else {
                    _playUiState.value = state.copy(
                        flippedCards = Pair(null, null)
                    )

                    Log.d(
                        PVM_TAG,
                        "checkPair() different characters - $playUiState"
                    )
                }
            }

            else -> return
        }
    }
}