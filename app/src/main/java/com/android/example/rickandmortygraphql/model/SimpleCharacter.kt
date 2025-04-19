package com.android.example.rickandmortygraphql.model


data class CharactersResponse(
    val results: List<SimpleCharacter>
)

data class SimpleCharacter(
    val name: String?,
    val image: String?,
    val status: CharacterStatus?,
    val species: String?,
    val origin: String?,
    val lastKnownLocation: String?,
)