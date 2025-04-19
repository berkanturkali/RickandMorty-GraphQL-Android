package com.android.example.rickandmortygraphql.data.abstraction

import com.android.example.rickandmortygraphql.model.SimpleCharacter

interface CharacterClient {
    suspend fun getCharacters(page: Int): List<SimpleCharacter>
}