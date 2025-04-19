package com.android.example.rickandmortygraphql.data

import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.SimpleCharacter
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
            image = character.image,
        )
    } ?: emptyList()
}