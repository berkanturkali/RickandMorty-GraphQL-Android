package com.android.example.rickandmortygraphql.model

import android.content.Context
import com.android.example.rickandmortygraphql.R

object CharacterFilterDataSource {

    fun getFilters(context: Context): List<CharacterFilter<*>> {
        return listOf(
            CharacterFilter(
                type = CharacterFilterType.Status,
                options = CharacterStatus.entries,
            ),
            CharacterFilter(
                type = CharacterFilterType.Species,
                options = context.resources.getStringArray(R.array.species_filter_options).toList(),
            ),
            CharacterFilter(
                type = CharacterFilterType.Gender,
                options = CharacterGender.entries,
            ),
        )
    }
}