package com.android.example.rickandmortygraphql.navigation

import kotlinx.serialization.Serializable


object RickAndMortyDestinations {
    @Serializable
    data object CharactersScreen

    @Serializable
    data object CharacterFilterOptionsScreen

    @Serializable
    data class CharacterFiltersScreen(
        val title: String,
        val filters: List<String>,
        val previouslySelectedFilter: String? = null,
    )

    @Serializable
    data class CharacterDetailsScreen(
        val id: String,
    )
}
