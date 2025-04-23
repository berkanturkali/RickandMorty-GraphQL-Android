package com.android.example.rickandmortygraphql.presentation.filter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import com.android.example.rickandmortygraphql.utils.noRippleClickable

@Composable
fun FilterOptionItem(
    title: String,
    selectedValue: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(modifier = modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .noRippleClickable {
            onClick()
        }) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.rickAndMortyColors.text.primary
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = selectedValue ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.rickAndMortyColors.text.primary,
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    tint = MaterialTheme.rickAndMortyColors.text.primary
                )
            }
        }
    }

}

@Preview
@Composable
private fun FilterOptionItemPrev() {
    RickAndMortyGraphQLTheme {
        FilterOptionItem(
            title = "Status",
            selectedValue = "Alive",
            onClick = {},
            modifier = Modifier.padding(8.dp)
        )
    }
}