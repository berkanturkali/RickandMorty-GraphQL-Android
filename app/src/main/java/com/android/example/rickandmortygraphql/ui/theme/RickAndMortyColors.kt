package com.android.example.rickandmortygraphql.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalRickAndMortyColorPalette = staticCompositionLocalOf { RickAndMortyColors() }

@Immutable
data class RickAndMortyColors(
    val background: BackgroundColors = BackgroundColors(),
    val text: TextColors = TextColors(),
    val icon: IconColors = IconColors(),
    val bottomBarColor: BottomBarColors = BottomBarColors()
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

@Immutable
data class IconColors(
    val primary: Color = Color.Unspecified,
)

@Immutable
data class BottomBarColors(
    val selected: Color = Color.Unspecified,
    val unselected: Color = Color.Unspecified
)