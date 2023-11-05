package com.hotwater.findthepairs.presentation.play.previews

import androidx.compose.runtime.Composable
import com.hotwater.findthepairs.presentation.play.PlayScreenSuccess
import com.hotwater.findthepairs.presentation.util.getRawPlayUiStateSuccess
import com.hotwater.findthepairs.presentation.util.PortraitPreviews

@PortraitPreviews
@Composable
fun PlayScreenSuccessPortraitPreview() {
    PlayScreenSuccess(
        uiState = getRawPlayUiStateSuccess(),
        onTurn = { })
}