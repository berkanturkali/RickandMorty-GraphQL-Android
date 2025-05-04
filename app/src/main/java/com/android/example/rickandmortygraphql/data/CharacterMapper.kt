package com.android.example.rickandmortygraphql.data

import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.DetailedCharacter
import com.android.example.rickandmortygraphql.model.Episode
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.example.CharacterQuery
import com.example.CharactersQuery

fun CharactersQuery.Characters.toSimpleCharacters(): List<SimpleCharacter> {
    return this.results?.filterNotNull()?.map { character ->
        SimpleCharacter(
            name = character.name,
            status = character.status?.let { status ->
                CharacterStatus.valueOf(status.replaceFirstChar {
                    it.uppercase()
                })
            },
            species = character.species,
            origin = character.origin?.name,
            lastKnownLocation = character.location?.name,
            image = character.image ?: "",
            id = character.id,
        )
    } ?: emptyList()
}

fun CharacterQuery.Character.toDetailedCharacter(): DetailedCharacter {
    return DetailedCharacter(
        id = id,
        image = image,
        status = status?.let { status ->
            CharacterStatus.valueOf(status.replaceFirstChar {
                it.uppercase()
            })
        },
        species = species,
        origin = origin?.name,
        lastKnownLocation = location?.name,
        episodes = episode.map {
            Episode(
                id = it?.id,
                name = it?.name,
                created = it?.air_date,
            )
        },
        name = name,
    )
}