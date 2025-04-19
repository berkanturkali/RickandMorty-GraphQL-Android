package com.android.example.rickandmortygraphql.presentation.characters.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.android.example.rickandmortygraphql.presentation.characters.components.CharacterItem
import com.android.example.rickandmortygraphql.presentation.characters.viewmodel.CharactersScreenViewModel

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersScreenViewModel = hiltViewModel<CharactersScreenViewModel>()
) {

    val characters by viewModel.characters

    CharactersScreenContent(
        characters = characters,
        modifier = modifier
    )
}

@Composable
private fun CharactersScreenContent(
    characters: List<SimpleCharacter>,
    modifier: Modifier = Modifier
) {
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