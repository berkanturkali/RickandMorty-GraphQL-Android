package com.android.example.rickandmortygraphql.presentation.character.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier
) {

    CharacterScreenContent(modifier = modifier)

}

@Composable
fun CharacterScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

    }
}
