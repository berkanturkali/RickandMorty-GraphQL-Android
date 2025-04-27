package com.android.example.rickandmortygraphql.data.implementation

import com.android.example.rickandmortygraphql.data.abstraction.CharacterClient
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import com.android.example.rickandmortygraphql.data.toSimpleCharacters
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.example.CharactersQuery
import javax.inject.Inject

class CharacterClientImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : CharacterClient {
    override suspend fun getCharacters(
        name: String?,
        filters: CharacterFilterEntity?,
        page: Int
    ): List<SimpleCharacter> {
        return apolloClient.query(
            CharactersQuery(
                page = page,
                name = Optional.presentIfNotNull(name),
                status = Optional.presentIfNotNull(filters?.status),
                species = Optional.presentIfNotNull(filters?.species),
                gender = Optional.presentIfNotNull(filters?.gender)
            )
        )
            .execute()
            .data
            ?.characters
            ?.toSimpleCharacters() ?: emptyList()
    }
}