package com.android.example.rickandmortygraphql.data.abstraction

import com.android.example.rickandmortygraphql.model.Location

interface LocationClient {

    suspend fun getLocations(page: Int): List<Location>
}