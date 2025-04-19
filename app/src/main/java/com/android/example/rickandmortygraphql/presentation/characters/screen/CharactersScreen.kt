package com.android.example.rickandmortygraphql.presentation.characters.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.android.example.rickandmortygraphql.presentation.characters.components.CharacterItem
import com.android.example.rickandmortygraphql.presentation.characters.components.CharactersSearchBar
import com.android.example.rickandmortygraphql.presentation.characters.viewmodel.CharactersScreenViewModel
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersScreenViewModel = hiltViewModel<CharactersScreenViewModel>()
) {

    val characters by viewModel.characters

    CharactersScreenContent(
        searchQuery = viewModel.searchQuery,
        characters = characters,
        onSearchQueryChanged = {
            viewModel.searchQuery = it
        },
        modifier = modifier
    )
}

@Composable

private fun CharactersScreenContent(
    searchQuery: String,
    characters: List<SimpleCharacter>,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        CharactersSearchBar(
            value = searchQuery,
            onValueChanged = onSearchQueryChanged,
        )
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(characters) {
                CharacterItem(
                    name = it.name ?: "",
                    image = it.image ?: "",
                    status = it.status ?: CharacterStatus.Unknown,
                    species = it.species ?: "",
                    location = it.lastKnownLocation ?: "",
                    origin = it.origin ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharactersScreenContentPrev() {
    RickAndMortyGraphQLTheme {
        CharactersScreenContent(
            searchQuery = "",
            characters = listOf(
                SimpleCharacter(
                    name = "Rick Sanchez",
                    status = CharacterStatus.Alive,
                    species = "Human",
                    origin = "Earth",
                    lastKnownLocation = "Earth",
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                ),
                SimpleCharacter(
                    name = "Rick Sanchez",
                    status = CharacterStatus.Alive,
                    species = "Human",
                    origin = "Earth",
                    lastKnownLocation = "Earth",
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                ),
                SimpleCharacter(
                    name = "Rick Sanchez",
                    status = CharacterStatus.Alive,
                    species = "Human",
                    origin = "Earth",
                    lastKnownLocation = "Earth",
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                ),
            ),
            onSearchQueryChanged = {}
        )
    }
}

