package com.android.example.rickandmortygraphql.presentation.filter.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.model.CharacterFilter
import com.android.example.rickandmortygraphql.model.CharacterFilterDataSource
import com.android.example.rickandmortygraphql.presentation.filter.components.FilterOptionItem
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import com.android.example.rickandmortygraphql.utils.noRippleClickable

@Composable
fun CharacterFilterOptionsScreen(
    navigateUp: () -> Unit,
    onCheckMarkClick: () -> Unit,
    onFilterOptionClick: (CharacterFilter<*>) -> Unit,
    modifier: Modifier = Modifier,
) {

    CharacterFilterOptionsScreenContent(
        modifier = modifier,
        navigateUp = navigateUp,
        onCheckMarkClick = onCheckMarkClick,
        onFilterOptionClick = onFilterOptionClick,
    )
}

@Composable
private fun CharacterFilterOptionsScreenContent(
    navigateUp: () -> Unit,
    onCheckMarkClick: () -> Unit,
    onFilterOptionClick: (CharacterFilter<*>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val enableCheckMark by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FiltersTopBar(
            enableCheckMark = enableCheckMark,
            onBackButtonClick = navigateUp,
            onCheckMarkClick = onCheckMarkClick,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CharacterFilterDataSource.getFilters(context).forEach { filter ->
                FilterOptionItem(
                    title = filter.title,
                    selectedValue = filter.selectedFilter,
                    onClick = {
                        onFilterOptionClick(filter)
                    },
                )
            }
        }

    }
}

@Composable
fun FiltersTopBar(
    enableCheckMark: Boolean,
    onBackButtonClick: () -> Unit,
    onCheckMarkClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.filter_options_title),
) {
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.rickAndMortyColors.text.primary) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(4.dp)
                        .noRippleClickable {
                            onBackButtonClick()
                        }
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Center)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = null,
                    tint = if (enableCheckMark) MaterialTheme.rickAndMortyColors.text.primary else MaterialTheme.rickAndMortyColors.text.gray,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(4.dp)
                        .noRippleClickable {
                            if (enableCheckMark) {
                                onCheckMarkClick()
                            }
                        }
                )

            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.rickAndMortyColors.text.gray,
                thickness = 0.2.dp
            )
        }
    }
}

@Composable
@Preview
fun CharacterFilterOptionsScreenContentPreview() {
    RickAndMortyGraphQLTheme {
        CharacterFilterOptionsScreenContent(
            navigateUp = {},
            onCheckMarkClick = {},
            onFilterOptionClick = {},
        )
    }
}