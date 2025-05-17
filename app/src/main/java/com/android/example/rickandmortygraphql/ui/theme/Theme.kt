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
import com.android.example.rickandmortygraphql.model.RickAndMortyTheme

val MaterialTheme.rickAndMortyColors: RickAndMortyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalRickAndMortyColorPalette.current

private val DarkThemeColors = RickAndMortyColors(
    background = BackgroundColors(
        primary = Color(0xFF0F0F0F),
        surface = Color(0xFF3C3E44),
    ),
    text = TextColors(
        primary = Color(0xFFFFFFFF),
        gray = Color(0xFF9E9E9E),
    ),
    icon = IconColors(
        primary = Color(0xFF007AFF)
    ),
    bottomBarColor = BottomBarColors(
        selected = Color(0xFFFFFFFF),
        unselected = Color(0xFF9E9E9E),
    )
)

private val LightThemeColors = RickAndMortyColors(
    background = BackgroundColors(
        primary = Color(0xFFFFFFFF),
        surface = Color(0xFFF5F5F5),
    ),
    text = TextColors(
        primary = Color(0xFF000000),
        gray = Color(0xFF9E9E9E),
    ),
    icon = IconColors(
        primary = Color(0xFF007AFF)
    ),
    bottomBarColor = BottomBarColors(
        selected = Color(0xFF000000),
        unselected = Color(0xFF9E9E9E),
    )
)

private val DimThemeColors = RickAndMortyColors(
    background = BackgroundColors(
        primary = Color(0xFF15202B),
        surface = Color(0XFF192734),
    ),
    text = TextColors(
        primary = Color(0xFFFFFFFF),
        gray = Color(0xFF9E9E9E),
    ),
    icon = IconColors(
        primary = Color(0xFF007AFF)
    ),
    bottomBarColor = BottomBarColors(
        selected = Color(0xFFFFFFFF),
        unselected = Color(0xFF9E9E9E),
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
    theme: RickAndMortyTheme = RickAndMortyTheme.DARK,
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

    val colorPalette = when(
        theme
    ) {
        RickAndMortyTheme.DARK -> DarkThemeColors
        RickAndMortyTheme.LIGHT -> LightThemeColors
        RickAndMortyTheme.DIM -> DimThemeColors
    }

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