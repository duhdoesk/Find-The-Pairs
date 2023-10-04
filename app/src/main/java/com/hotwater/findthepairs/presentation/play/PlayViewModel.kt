package com.hotwater.findthepairs.presentation.play

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.presentation.util.getRawListOfCharacters
import com.hotwater.findthepairs.presentation.util.getRawPlayUiStateSuccess
import com.hotwater.findthepairs.presentation.util.getRawTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayViewModel : ViewModel() {

    private val _playUiState = MutableStateFlow<PlayUiState>(PlayUiState.Loading)
    val playUiState = _playUiState.asStateFlow()

    init {
        _playUiState.value = getRawPlayUiStateSuccess()
    }
}