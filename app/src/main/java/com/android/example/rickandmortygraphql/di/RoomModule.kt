package com.android.example.rickandmortygraphql.di

import android.app.Application
import androidx.room.Room
import com.android.example.rickandmortygraphql.data.cache.db.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    const val DB_NAME = "rick_and_morty.db"

    @[Provides Singleton]
    fun provideDatabase(
        app: Application,
    ): RickAndMortyDatabase {
        return Room.databaseBuilder(
            app,
            RickAndMortyDatabase::class.java,
            DB_NAME,
        ).build()
    }

    @[Provides Singleton]
    fun provideCharacterFilterDao(
        db: RickAndMortyDatabase,
    ) = db.characterFilterDao()
}