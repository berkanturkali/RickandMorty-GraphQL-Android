package com.android.example.rickandmortygraphql.presentation.characters.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun CharacterItemVerticalSection(
    sectionTitle: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.rickAndMortyColors.text.gray
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.rickAndMortyColors.text.primary
        )
    }
}