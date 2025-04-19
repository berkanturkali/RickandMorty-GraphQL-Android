package com.android.example.rickandmortygraphql.presentation.characters.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun CharactersSearchBar(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.rickAndMortyColors.background.surface,
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChanged(it)
            },
            textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.rickAndMortyColors.text.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = stringResource(R.string.search_by_name),
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.rickAndMortyColors.text.gray)
                            )
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = null,
                                modifier = Modifier
                                    .alpha(0f)
                                    .padding(start = 8.dp)
                            )
                        } else {
                            innerTextField()
                        }
                    }
                    AnimatedVisibility(
                        visible = value.isNotEmpty(),
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = "Clear text",
                            tint = MaterialTheme.rickAndMortyColors.text.primary,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .clickable { onValueChanged("") }
                        )
                    }

                }

            }
        )
    }
}

@Preview
@Composable
private fun CharactersSearchBarPrev() {
    RickAndMortyGraphQLTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            CharactersSearchBar(
                value = "",
                onValueChanged = {},
                modifier = Modifier.fillMaxWidth()
            )
            CharactersSearchBar(
                value = "Rick Sanchez",
                onValueChanged = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}