package com.android.example.rickandmortygraphql.model

import com.android.example.rickandmortygraphql.R

data class CharacterFilter<T>(
    val type: CharacterFilterType,
    val options: List<T>,
    val selectedFilter: String? = null,
)

sealed class CharacterFilterType(val titleResId: Int) {
    data object Status : CharacterFilterType(R.string.status)
    data object Species : CharacterFilterType(R.string.species)
    data object Gender : CharacterFilterType(R.string.gender)
}
