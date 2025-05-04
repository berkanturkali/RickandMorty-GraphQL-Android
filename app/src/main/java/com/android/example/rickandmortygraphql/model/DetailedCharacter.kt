package com.android.example.rickandmortygraphql.model

data class DetailedCharacter(
    override val id: String?,
    override val name: String?,
    override val image: String?,
    override val status: CharacterStatus?,
    override val species: String?,
    override val origin: String?,
    override val lastKnownLocation: String?,
    val episodes: List<Episode>,
) : Character

data class Episode(
    val id: String? = null,
    val name: String? = null,
    val airDate: String? = null,
    val episode: String? = null,
    val created: String? = null,
)