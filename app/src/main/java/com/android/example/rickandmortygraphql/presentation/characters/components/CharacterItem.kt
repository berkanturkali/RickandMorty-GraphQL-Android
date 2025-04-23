package com.android.example.rickandmortygraphql.presentation.characters.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import timber.log.Timber

@Composable
fun CharacterItem(
    name: String,
    image: String,
    status: CharacterStatus,
    species: String,
    location: String,
    origin: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.rickAndMortyColors.background.surface,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = image,
                error = {

                    Timber.e("Error loading image: $it")
                    Image(
                        painter = painterResource(id = R.drawable.ic_broken_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
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
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart
            )

            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                CharacterNameAndSpecies(
                    name = name,
                    status = status,
                    species = species,
                )

                CharacterItemVerticalSection(
                    sectionTitle = stringResource(R.string.last_known_location),
                    text = location,
                )

                CharacterItemVerticalSection(
                    sectionTitle = stringResource(R.string.first_seen_in),
                    text = origin,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharacterItemPrev() {
    RickAndMortyGraphQLTheme {
        CharacterItem(
            name = "Rick Sanchez",
            status = CharacterStatus.Alive,
            species = "Human",
            location = "Earth (C-137)",
            origin = "Earth (C-137)",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        )
    }
}