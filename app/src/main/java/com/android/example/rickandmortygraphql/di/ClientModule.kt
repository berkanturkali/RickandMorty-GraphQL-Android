package com.android.example.rickandmortygraphql.di

import com.android.example.rickandmortygraphql.data.abstraction.CharacterClient
import com.android.example.rickandmortygraphql.data.abstraction.LocationClient
import com.android.example.rickandmortygraphql.data.implementation.CharacterClientImpl
import com.android.example.rickandmortygraphql.data.implementation.LocationClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ClientModule {

    @[Provides Singleton]
    fun provideCharacterClient(
        characterClientImpl: CharacterClientImpl
    ): CharacterClient {
        return characterClientImpl
    }


    @[Provides Singleton]
    fun provideLocationClient(
        locationClientImpl: LocationClientImpl
    ): LocationClient {
        return locationClientImpl
    }
}