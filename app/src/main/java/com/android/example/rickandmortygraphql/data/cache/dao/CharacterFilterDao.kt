package com.android.example.rickandmortygraphql.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity

@Dao
interface CharacterFilterDao {

    @Query("SELECT * FROM character_filter")
    suspend fun getCharacterFilters(): CharacterFilterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterFilter(characterFilter: CharacterFilterEntity)

    @Delete
    suspend fun deleteCharacterFilter(characterFilter: CharacterFilterEntity)
}