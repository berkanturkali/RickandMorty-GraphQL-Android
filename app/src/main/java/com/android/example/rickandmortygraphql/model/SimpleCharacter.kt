package com.android.example.rickandmortygraphql.model

interface Character {
    val id: String?
    val name: String?
    val image: String?
    val status: CharacterStatus?
    val species: String?
    val origin: String?
    val lastKnownLocation: String?
}

data class CharactersResponse(
    val results: List<SimpleCharacter>
)

data class SimpleCharacter(
    override val id: String?,
    override val name: String?,
    override val image: String?,
    override val status: CharacterStatus?,
    override val species: String?,
    override val origin: String?,
    override val lastKnownLocation: String?
) : Character