package com.android.example.rickandmortygraphql.data.implementation

import com.android.example.rickandmortygraphql.data.abstraction.LocationClient
import com.android.example.rickandmortygraphql.model.Location
import com.apollographql.apollo.ApolloClient
import com.example.LocationsQuery
import javax.inject.Inject

class LocationClientImpl @Inject constructor(
    private val apolloClient: ApolloClient,
) : LocationClient {
    override suspend fun getLocations(page: Int): List<Location> {
        val response = apolloClient.query(
            LocationsQuery(
                page = page,
            )
        ).execute()

        return response.data?.locations?.results?.map {
            Location(
                id = it?.id ?: "",
                name = it?.name ?: "",
                type = it?.type ?: "",
            )
        } ?: emptyList()
    }
}