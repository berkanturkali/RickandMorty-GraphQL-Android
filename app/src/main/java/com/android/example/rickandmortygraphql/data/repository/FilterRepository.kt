package com.android.example.rickandmortygraphql.data.repository

import com.android.example.rickandmortygraphql.data.cache.dao.CharacterFilterDao
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import javax.inject.Inject

class FilterRepository @Inject constructor(
    private val characterFilterDao: CharacterFilterDao,
) {

    fun getCharacterFilters() = characterFilterDao.getCharacterFilters()

    suspend fun insertCharacterFilter(characterFilter: CharacterFilterEntity) =
        characterFilterDao.insertCharacterFilter(characterFilter)

    suspend fun deleteCharacterFilter(characterFilter: CharacterFilterEntity) =
        characterFilterDao.deleteCharacterFilter(characterFilter)
}