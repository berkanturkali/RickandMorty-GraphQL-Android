package com.android.example.rickandmortygraphql.presentation.character.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun EpisodeItem(
    name: String,
    date: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .width(120.dp)
            .height(150.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(0.2.dp, Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                painter = painterResource(R.drawable.ic_movieclapper),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .border(0.2.dp, Color.LightGray, shape = CircleShape)
                    .padding(6.dp)
            )

            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.rickAndMortyColors.text.primary,
                textAlign = TextAlign.Center
            )


            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.rickAndMortyColors.text.primary,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
private fun EpisodeItemPrev() {
    RickAndMortyGraphQLTheme {
        EpisodeItem(
            name = "Pilot",
            date = "2017-11-10T12:56:33.916Z"
        )
    }
}