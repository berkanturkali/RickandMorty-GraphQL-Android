package com.android.example.rickandmortygraphql.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import com.android.example.rickandmortygraphql.utils.LocalNavController
import com.android.example.rickandmortygraphql.utils.noRippleClickable

@Composable
fun RickAndMortyBackButton(
    modifier: Modifier = Modifier
) {

    val navController = LocalNavController.current
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.rickAndMortyColors.background.surface.copy(alpha = 0.5f)
        ),
        modifier = modifier
            .size(28.dp)
            .padding(4.dp)
            .border(0.2.dp, Color.LightGray, CircleShape)
            .noRippleClickable {
                navController.navigateUp()
            }
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
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