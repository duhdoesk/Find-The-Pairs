package com.hotwater.findthepairs.presentation.util

import android.util.Log
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.domain.model.Theme
import com.hotwater.findthepairs.presentation.play.CardState
import com.hotwater.findthepairs.presentation.play.GameState
import com.hotwater.findthepairs.presentation.play.PlayCard
import com.hotwater.findthepairs.presentation.play.PlayUiState
import kotlin.random.Random

fun getRawPlayUiStateSuccess(): PlayUiState.Success {
    return PlayUiState.Success(
        theme = getRawTheme(),
        cards = rawTesting(),
        flipped = emptyList(),
        found = emptyList(),
        gameState = GameState.PLAYING
    )
}

fun getRawTheme(): Theme {
    return Theme(
        themeId = "1",
        themeName = "Raw Theme",
        themePicture = "https://i.imgur.com/UCympxw.jpg"
    )
}

fun getRawCharacter(characterId: String = "1"): Character {
    return Character(
        characterId = characterId,
        characterName = "Raw Character",
        characterPicture = "https://i.imgur.com/UCympxw.jpg",
        characterThemeId = "1"
    )
}

fun getRawListOfCharacters(listSize: Int): List<Character> {
    return List(listSize) {
        getRawCharacter(Random.nextInt(from = 1, until = 21).toString())
    }
}

fun rawTesting(): List<PlayCard> {
    val charactersList = getRawListOfCharacters(12)
    Log.d("raw testing", charactersList.size.toString())

    val doubledList = (charactersList + charactersList).shuffled()
    Log.d("raw testing", doubledList.size.toString())

    val playingCardList = mutableListOf<PlayCard>()

    doubledList.forEach { character ->
        playingCardList.add(PlayCard(character, CardState.HIDDEN))
    }

    return playingCardList
}