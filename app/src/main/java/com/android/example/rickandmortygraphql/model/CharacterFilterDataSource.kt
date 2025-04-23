package com.android.example.rickandmortygraphql.model

import android.content.Context
import com.android.example.rickandmortygraphql.R

object CharacterFilterDataSource {

    fun getFilters(context: Context): List<CharacterFilter<*>> {
        return listOf(
            CharacterFilter(
                title = context.getString(R.string.status_filter_title),
                options = CharacterStatus.entries,
            ),
            CharacterFilter(
                title = context.getString(R.string.species_filter_title),
                options = context.resources.getStringArray(R.array.species_filter_options).toList(),
            ),
            CharacterFilter(
                title = context.getString(R.string.gender_filter_title),
                options = CharacterGender.entries,
            ),
        )
    }
}