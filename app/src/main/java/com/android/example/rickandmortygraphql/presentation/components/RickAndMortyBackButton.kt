package com.android.example.rickandmortygraphql.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.utils.LocalNavController
import com.android.example.rickandmortygraphql.utils.noRippleClickable

@Composable
fun RickAndMortyBackButton(
    modifier: Modifier = Modifier,
    size: Dp = 28.dp,
    onClick: () -> Unit = {},
) {

    val navController = LocalNavController.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .border(0.2.dp, Color.LightGray, CircleShape)
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
            .noRippleClickable {
                onClick()
                navController.navigateUp()
            }
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
private fun RickAndMortyBackButtonPrev() {
    RickAndMortyGraphQLTheme {
        CompositionLocalProvider(LocalNavController provides rememberNavController()) {
            RickAndMortyBackButton()
        }
    }
}