package com.android.example.rickandmortygraphql.di

import com.android.example.rickandmortygraphql.BuildConfig
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ApolloModule {

    @[Provides Singleton]
    fun provideApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
//                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()

        return ApolloClient.Builder()
            .okHttpClient(okHttpClient)
            .serverUrl(BuildConfig.GRAPHQL_BASE_URL)
            .build()
    }
}