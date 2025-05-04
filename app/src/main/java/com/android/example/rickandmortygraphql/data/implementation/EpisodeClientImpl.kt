package com.android.example.rickandmortygraphql.data.implementation

import com.android.example.rickandmortygraphql.data.abstraction.EpisodeClient
import com.android.example.rickandmortygraphql.model.Episode
import com.apollographql.apollo.ApolloClient
import com.example.EpisodesQuery
import javax.inject.Inject

class EpisodeClientImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : EpisodeClient {
    override suspend fun getEpisodes(page: Int): List<Episode> {
        val response = apolloClient.query(
            EpisodesQuery(page)
        ).execute()

        return response.data?.episodes?.results?.map {
            Episode(
                id = it?.id,
                name = it?.name,
                airDate = it?.air_date,
                episode = it?.episode,
                created = null,
            )
        } ?: emptyList()
    }
}