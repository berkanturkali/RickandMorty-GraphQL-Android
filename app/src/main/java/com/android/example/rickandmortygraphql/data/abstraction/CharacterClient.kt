package com.android.example.rickandmortygraphql.data.abstraction

import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import com.android.example.rickandmortygraphql.model.DetailedCharacter
import com.android.example.rickandmortygraphql.model.SimpleCharacter

interface CharacterClient {
    suspend fun getCharacters(name: String?,filters: CharacterFilterEntity?, page: Int): List<SimpleCharacter>

    suspend fun getCharacter(id: String): DetailedCharacter?
}