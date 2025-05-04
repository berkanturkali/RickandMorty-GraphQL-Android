package com.android.example.rickandmortygraphql.presentation.locations.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun LocationItem(
    location: String,
    type: String,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null,
                    tint = MaterialTheme.rickAndMortyColors.icon.primary,
                )
            }

            HorizontalSection(
                title = stringResource(R.string.location),
                text = location,
            )

            HorizontalSection(
                title = stringResource(R.string.type),
                text = type,
            )
        }
    }

}

@Preview
@Composable
private fun LocationItemPrev() {
    RickAndMortyGraphQLTheme {
        LocationItem(
            location = "Earth (C-137)",
            type = "Planet",
        )
    }
}