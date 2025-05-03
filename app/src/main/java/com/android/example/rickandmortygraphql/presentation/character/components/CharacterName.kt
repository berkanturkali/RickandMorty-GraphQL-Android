package com.android.example.rickandmortygraphql.presentation.character.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun CharacterName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        text = name,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.rickAndMortyColors.text.primary,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun CharacterNamePrev() {
    RickAndMortyGraphQLTheme {
        CharacterName(
            name = "Rick Sanchez"
        )
    }
}