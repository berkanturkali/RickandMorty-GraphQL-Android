package com.android.example.rickandmortygraphql.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.example.rickandmortygraphql.R

enum class BottomBarItems(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    CHARACTERS(R.drawable.ic_groups, R.string.characters),
    LOCATIONS(R.drawable.ic_location, R.string.locations),
    EPISODES(R.drawable.ic_movieclapper, R.string.episodes)
}