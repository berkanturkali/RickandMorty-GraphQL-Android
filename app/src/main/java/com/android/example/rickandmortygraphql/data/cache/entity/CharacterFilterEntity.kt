package com.android.example.rickandmortygraphql.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "character_filter",
)
data class CharacterFilterEntity(
    @PrimaryKey
    val id: UUID,
    @ColumnInfo("status")
    val status: String? = null,
    @ColumnInfo("species")
    val species: String? = null,
    @ColumnInfo("gender")
    val gender: String? = null,
)