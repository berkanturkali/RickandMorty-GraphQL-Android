package com.android.example.rickandmortygraphql.presentation.character.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.Episode
import com.android.example.rickandmortygraphql.presentation.character.components.CharacterImage
import com.android.example.rickandmortygraphql.presentation.character.components.CharacterName
import com.android.example.rickandmortygraphql.presentation.character.components.DetailScreenHorizontalSection
import com.android.example.rickandmortygraphql.presentation.character.components.EpisodesSection
import com.android.example.rickandmortygraphql.presentation.character.viewmodel.CharacterScreenViewModel
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.utils.LocalNavController

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterScreenViewModel = hiltViewModel<CharacterScreenViewModel>()
) {

    val character = viewModel.character

    CharacterScreenContent(
        modifier = modifier,
        image = character?.image ?: "",
        name = character?.name ?: "",
        status = character?.status ?: CharacterStatus.Unknown,
        species = character?.species ?: "",
        location = character?.lastKnownLocation ?: "",
        origin = character?.origin ?: "",
        episodes = character?.episodes ?: emptyList()
    )

}

@Composable
fun CharacterScreenContent(
    image: String,
    name: String,
    status: CharacterStatus,
    species: String,
    location: String,
    origin: String,
    episodes: List<Episode>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CharacterImage(
            image = image
        )

        CharacterName(
            name = name
        )

        HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)

        DetailScreenHorizontalSection(
            title = stringResource(R.string.status),
            text = status.name
        )
        DetailScreenHorizontalSection(
            title = stringResource(R.string.species),
            text = species
        )

        DetailScreenHorizontalSection(
            title = stringResource(R.string.last_known_location),
            text = location
        )

        DetailScreenHorizontalSection(
            title = stringResource(R.string.origin),
            text = origin
        )
        if (episodes.isNotEmpty()) {
            EpisodesSection(
                episodes = episodes
            )
        }
    }
}

@Preview
@Composable
private fun CharacterScreenPrev() {
    RickAndMortyGraphQLTheme {
        CompositionLocalProvider(LocalNavController provides rememberNavController()) {
            CharacterScreenContent(
                name = "Rick Sanchez",
                status = CharacterStatus.Alive,
                species = "Human",
                location = "Earth (C-137)",
                origin = "Earth (C-137)",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                episodes = emptyList()
            )
        }
    }
}
