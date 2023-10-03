package com.hotwater.findthepairs.presentation.play

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.presentation.util.getRawListOfCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayViewModel : ViewModel() {

    private val _playUiState = MutableStateFlow<PlayUiState>(PlayUiState.Loading)
    val playUiState = _playUiState.asStateFlow()

    fun rawTesting(): List<Character> {
        val charactersList = getRawListOfCharacters(14)
        Log.d("raw testing", charactersList.size.toString())

        val doubledList = (charactersList + charactersList).shuffled()
        Log.d("raw testing", doubledList.size.toString())

        return doubledList
    }
}