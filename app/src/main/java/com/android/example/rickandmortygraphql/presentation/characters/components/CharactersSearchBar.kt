package com.android.example.rickandmortygraphql.presentation.characters.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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

    val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
    var isFocused by remember { androidx.compose.runtime.mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(0.5.dp, color = Color.LightGray),
                shape = RoundedCornerShape(16.dp)
            ),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChanged(it)
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.rickAndMortyColors.text.primary),
            cursorBrush = SolidColor(MaterialTheme.rickAndMortyColors.text.primary),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .focusRequester(focusRequester)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty() && !isFocused) {
                            Text(
                                text = stringResource(R.string.search_by_name),
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.rickAndMortyColors.text.gray),
                            )
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = null,
                                modifier = Modifier
                                    .alpha(0f)
                                    .padding(start = 8.dp)
                            )
                        }
                        innerTextField()
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
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) { onValueChanged("") }
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