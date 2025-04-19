package com.android.example.rickandmortygraphql.model

import androidx.annotation.ColorRes
import com.android.example.rickandmortygraphql.R


enum class CharacterStatus(@ColorRes val color: Int) {
    Alive(R.color.green),
    Dead(R.color.red),
    Unknown(R.color.gray),
}