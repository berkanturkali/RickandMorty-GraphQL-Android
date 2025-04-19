package com.android.example.rickandmortygraphql.presentation.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun CharacterNameAndSpecies(
    modifier: Modifier = Modifier,
    name: String,
    status: CharacterStatus,
    species: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.rickAndMortyColors.text.primary
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(color = colorResource(id = status.color), shape = CircleShape)
            )
            Text(
                text = stringResource(R.string.status_and_species, status.name, species),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.rickAndMortyColors.text.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(0.dp, 1.dp)
            )
        }
    }
}


@Preview
@Composable
private fun CharacterNameAndSpeciesPrev() {
    RickAndMortyGraphQLTheme {
        CharacterNameAndSpecies(
            name = "Rick Sanchez",
            status = CharacterStatus.Alive,
            species = "Human",
            modifier = Modifier.fillMaxWidth()
        )
    }
}