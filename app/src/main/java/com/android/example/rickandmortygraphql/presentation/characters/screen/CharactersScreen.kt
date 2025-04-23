package com.android.example.rickandmortygraphql.presentation.characters.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.android.example.rickandmortygraphql.presentation.characters.components.CharacterItem
import com.android.example.rickandmortygraphql.presentation.characters.components.CharactersSearchBar
import com.android.example.rickandmortygraphql.presentation.characters.viewmodel.CharactersScreenViewModel
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun CharactersScreen(
    navigateToFilterScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharactersScreenViewModel = hiltViewModel<CharactersScreenViewModel>()
) {

    val characters by viewModel.characters

    CharactersScreenContent(
        searchQuery = viewModel.searchQuery,
        characters = characters,
        navigateToFilterScreen = navigateToFilterScreen,
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
    navigateToFilterScreen: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = MaterialTheme.rickAndMortyColors.text.primary,
                modifier = Modifier
                    .border(
                        1.dp,
                        color = MaterialTheme.rickAndMortyColors.text.primary,
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .size(20.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    ) {
                        navigateToFilterScreen()
                    }
            )
        }
        CharactersSearchBar(
            value = searchQuery,
            onValueChanged = onSearchQueryChanged,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
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
            onSearchQueryChanged = {},
            navigateToFilterScreen = { /*TODO*/ },
        )
    }
}

