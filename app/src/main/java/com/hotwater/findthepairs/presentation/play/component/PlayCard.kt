package com.hotwater.findthepairs.presentation.play.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
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
import com.hotwater.findthepairs.presentation.play.PlayCard

@Composable
fun PlayCard(
    playCard: PlayCard,
    onClick: () -> Unit
) {

    val rotation = animateFloatAsState(
        targetValue = playCard.cardState.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ),
        label = ""
    )

    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .graphicsLayer {
                rotationY = rotation.value
            }
    ) {
        when (playCard.cardState) {
            CardState.Found -> {

            }

            CardState.FaceUp -> {
                FaceUpCard(character = playCard.character)
            }

            CardState.FaceDown -> {
                FaceDownCard(
                    onClick = { onClick() }
                )
            }
        }
    }
}

@Composable
fun FaceUpCard(
    character: Character
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
                .graphicsLayer {
                    rotationY = 180f
                },
        )
    }
}

@Composable
fun FaceDownCard(
    onClick: () -> Unit
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.hot_water_logo_square),
            contentDescription = "Art for the back of the card",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}