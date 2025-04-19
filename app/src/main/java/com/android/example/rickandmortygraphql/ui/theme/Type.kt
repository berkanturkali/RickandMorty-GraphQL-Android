package com.android.example.rickandmortygraphql.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.example.rickandmortygraphql.R

val LuckiestGuyFontFamily = FontFamily(
    Font(R.font.luckiest_guy_regular, FontWeight.Normal)
)


val RickAndMortyTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 57.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = LuckiestGuyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
    )

)