package com.android.example.rickandmortygraphql.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterFilterDao {

    @Query("SELECT * FROM character_filter")
    fun getCharacterFilters(): Flow<CharacterFilterEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterFilter(characterFilter: CharacterFilterEntity)

    @Delete
    suspend fun deleteCharacterFilter(characterFilter: CharacterFilterEntity)
}