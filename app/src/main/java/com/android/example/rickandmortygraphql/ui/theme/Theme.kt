package com.android.example.rickandmortygraphql.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val MaterialTheme.rickAndMortyColors: RickAndMortyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalRickAndMortyColorPalette.current

private val DarkThemeColors = RickAndMortyColors(
    background = BackgroundColors(
        primary = Color(0xFF272B33),
        surface = Color(0xFF3C3E44),
    ),
    text = TextColors(
        primary = Color(0xFFFFFFFF),
        gray = Color(0xFF9E9E9E),
    )
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun RickAndMortyGraphQLTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val colorPalette = DarkThemeColors

    CompositionLocalProvider(
        LocalRickAndMortyColorPalette provides colorPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = RickAndMortyTypography,
            content = content
        )
    }
}