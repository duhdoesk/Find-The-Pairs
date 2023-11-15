package com.hotwater.findthepairs.presentation.play.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.hotwater.findthepairs.R
import com.hotwater.findthepairs.domain.model.Character
import com.hotwater.findthepairs.presentation.play.CardState

@Composable
fun PlayCard(
    character: Character,
    cardState: CardState,
    onTurn: () -> Unit
) {

    when (cardState) {
        CardState.FOUND -> {

        }

        CardState.FLIPPED -> {
            FaceUpCard(character = character)
        }

        CardState.HIDDEN -> {
            FaceDownCard(
                onTurn = { onTurn() }
            )
        }
    }
}

@Composable
fun FaceUpCard(
    character: Character
) {

    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {

        Surface(color = MaterialTheme.colorScheme.background) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.characterPicture)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = "Character Picture",
                placeholder = painterResource(id = R.drawable.baseline_pause_24),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun FaceDownCard(
    onTurn: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable {
                Log.d(
                    "FaceDownCard",
                    "onTurn"
                )
                onTurn()
            }
    ) {

        Surface(color = MaterialTheme.colorScheme.background) {
            Image(
                painter = painterResource(id = R.drawable.hot_water_logo_square),
                contentDescription = "Art for the back of the card",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}