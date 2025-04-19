package com.android.example.rickandmortygraphql.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalRickAndMortyColorPalette = staticCompositionLocalOf { RickAndMortyColors() }

@Immutable
data class RickAndMortyColors(
    val background: BackgroundColors = BackgroundColors(),
    val text: TextColors = TextColors(),
)

@Immutable
data class BackgroundColors(
    val primary: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
)

@Immutable
data class TextColors(
    val primary: Color = Color.Unspecified,
    val gray: Color = Color.Unspecified,
)