package com.android.example.rickandmortygraphql.data.abstraction

import com.android.example.rickandmortygraphql.model.Episode

interface EpisodeClient {

    suspend fun getEpisodes(page: Int = 1): List<Episode>
}