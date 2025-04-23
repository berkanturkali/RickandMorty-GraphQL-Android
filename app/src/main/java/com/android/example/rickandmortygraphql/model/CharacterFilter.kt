package com.android.example.rickandmortygraphql.model

data class CharacterFilter<T>(
    val title: String,
    val options: List<T>,
    val selectedFilter: String? = null,
)