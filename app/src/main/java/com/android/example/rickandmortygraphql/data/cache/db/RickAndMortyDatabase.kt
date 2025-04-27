package com.android.example.rickandmortygraphql.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.example.rickandmortygraphql.data.cache.dao.CharacterFilterDao
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity

@Database(entities = [CharacterFilterEntity::class], version = 1, exportSchema = false)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterFilterDao(): CharacterFilterDao
}