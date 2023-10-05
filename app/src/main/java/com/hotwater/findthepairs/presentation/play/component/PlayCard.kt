package com.hotwater.findthepairs.presentation.play.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hotwater.findthepairs.R
import com.hotwater.findthepairs.domain.model.Character

@Composable
fun PlayCard(
    character: Character,
    turned: Boolean
) {

    if (turned) FaceUpCard(character = character) else FaceDownCard()
}

@Composable
fun FaceUpCard(
    character: Character
) {

}

@Composable
fun FaceDownCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Image(
                painter = painterResource(id = R.drawable.back_of_the_card),
                contentDescription = "Art for the back of the card",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}