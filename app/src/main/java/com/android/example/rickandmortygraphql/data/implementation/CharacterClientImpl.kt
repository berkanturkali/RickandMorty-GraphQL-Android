package com.android.example.rickandmortygraphql.data.implementation

import com.android.example.rickandmortygraphql.data.abstraction.CharacterClient
import com.android.example.rickandmortygraphql.data.toSimpleCharacters
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.apollographql.apollo.ApolloClient
import com.example.CharactersQuery
import javax.inject.Inject

class CharacterClientImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : CharacterClient {
    override suspend fun getCharacters(page: Int): List<SimpleCharacter> {
        return apolloClient.query(
            CharactersQuery(
                page = page
            )
        )
            .execute()
            .data
            ?.characters
            ?.toSimpleCharacters() ?: emptyList()
    }
}