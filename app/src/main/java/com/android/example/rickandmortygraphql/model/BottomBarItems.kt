package com.android.example.rickandmortygraphql.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.navigation.RickAndMortyDestinations

enum class BottomBarItems(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val destination: RickAndMortyDestinations,
) {
    CHARACTERS(
        R.drawable.ic_groups,
        R.string.characters,
        RickAndMortyDestinations.CharactersScreen
    ),
    LOCATIONS(R.drawable.ic_location, R.string.locations, RickAndMortyDestinations.LocationsScreen),
    EPISODES(
        R.drawable.ic_movieclapper,
        R.string.episodes,
        RickAndMortyDestinations.EpisodesScreen
    ),
}