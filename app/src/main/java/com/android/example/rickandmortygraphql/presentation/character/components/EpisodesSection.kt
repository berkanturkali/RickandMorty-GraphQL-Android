package com.android.example.rickandmortygraphql.presentation.character.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.model.Episode
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun EpisodesSection(
    episodes: List<Episode>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(R.string.episodes),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.rickAndMortyColors.text.primary,
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(episodes) { episode ->
                EpisodeItem(
                    name = episode.name ?: "",
                    date = episode.created ?: "",
                )
            }
        }
    }
}