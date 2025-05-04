package com.android.example.rickandmortygraphql.navigation

import kotlinx.serialization.Serializable


sealed class RickAndMortyDestinations {
    @Serializable
    data object CharactersScreen: RickAndMortyDestinations()

    @Serializable
    data object CharacterFilterOptionsScreen : RickAndMortyDestinations()

    @Serializable
    data class CharacterFiltersScreen(
        val title: String,
        val filters: List<String>,
        val previouslySelectedFilter: String? = null,
    ) : RickAndMortyDestinations()

    @Serializable
    data class CharacterDetailsScreen(
        val id: String,
    ) : RickAndMortyDestinations()

    @Serializable
    data object LocationsScreen: RickAndMortyDestinations()

    @Serializable
    data object EpisodesScreen : RickAndMortyDestinations()
}
