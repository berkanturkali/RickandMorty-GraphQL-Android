package com.android.example.rickandmortygraphql.di

import com.android.example.rickandmortygraphql.BuildConfig
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ApolloModule {

    @[Provides Singleton]
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BuildConfig.GRAPHQL_BASE_URL)
            .build()
    }
}