package com.hotwater.findthepairs.presentation.util

import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.domain.model.Theme
import kotlin.random.Random

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