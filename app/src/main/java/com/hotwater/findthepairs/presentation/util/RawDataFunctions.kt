package com.hotwater.findthepairs.presentation.util

import android.util.Log
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.domain.model.Theme
import com.hotwater.findthepairs.presentation.play.PlayUiState
import com.hotwater.findthepairs.presentation.play.PlayingPhase
import kotlin.random.Random

fun getRawPlayUiStateSuccess(): PlayUiState.Success {
    return PlayUiState.Success(
        theme = getRawTheme(),
        foundPairs = emptyList(),
        notFoundPairs = rawTesting(),
        playingPhase = PlayingPhase.RUNNING
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

fun rawTesting(): List<Character> {
    val charactersList = getRawListOfCharacters(14)
    Log.d("raw testing", charactersList.size.toString())

    val doubledList = (charactersList + charactersList).shuffled()
    Log.d("raw testing", doubledList.size.toString())

    return doubledList
}