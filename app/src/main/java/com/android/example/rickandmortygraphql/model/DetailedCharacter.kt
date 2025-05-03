package com.android.example.rickandmortygraphql.model

data class DetailedCharacter(
    override val id: String?,
    override val name: String?,
    override val image: String?,
    override val status: CharacterStatus?,
    override val species: String?,
    override val origin: String?,
    override val lastKnownLocation: String?,
    val episode: List<Episode>,
) : Character

data class Episode(
    val id: String?,
    val name: String?,
    val created: String?,
)