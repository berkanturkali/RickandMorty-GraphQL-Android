package com.android.example.rickandmortygraphql.presentation.character.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil3.compose.SubcomposeAsyncImage
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.presentation.components.RickAndMortyBackButton
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.utils.LocalNavController
import timber.log.Timber

@Composable
fun CharacterImage(
    image: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RickAndMortyBackButton(
            size = 28.dp,
            modifier = Modifier.padding(2.dp)
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
        ) {

            SubcomposeAsyncImage(
                model = image,
                error = {

                    Timber.e("Error loading image: $it")
                    Image(
                        painter = painterResource(id = R.drawable.ic_broken_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(8.dp)
                            .align(Alignment.Center)
                    )

                },
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Center),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Preview
@Composable
private fun CharacterImagePrev() {
    RickAndMortyGraphQLTheme {
        CompositionLocalProvider(LocalNavController provides rememberNavController()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CharacterImage(image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
            }
        }
    }
}