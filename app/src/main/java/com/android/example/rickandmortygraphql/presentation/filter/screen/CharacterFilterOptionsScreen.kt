package com.android.example.rickandmortygraphql.presentation.filter.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import com.android.example.rickandmortygraphql.model.CharacterFilter
import com.android.example.rickandmortygraphql.model.CharacterFilterDataSource
import com.android.example.rickandmortygraphql.model.CharacterFilterType
import com.android.example.rickandmortygraphql.presentation.components.RickAndMortyBackButton
import com.android.example.rickandmortygraphql.presentation.filter.components.FilterOptionItem
import com.android.example.rickandmortygraphql.presentation.filter.viewmodel.CharacterFilterViewModel
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import com.android.example.rickandmortygraphql.utils.LocalNavController
import com.android.example.rickandmortygraphql.utils.noRippleClickable
import java.util.UUID

@Composable
fun CharacterFilterOptionsScreen(
    selectedFilter: String?,
    navigateUp: () -> Unit,
    onCheckMarkClick: (List<CharacterFilter<*>>) -> Unit,
    onFilterOptionClick: (CharacterFilter<*>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterFilterViewModel = hiltViewModel(),
) {

    val filters = viewModel.filters

    val context = LocalContext.current

    viewModel.setSelectedFilter(selectedFilter)

    CharacterFilterOptionsScreenContent(
        modifier = modifier,
        onCheckMarkClick = {
            var filterEntity =
                CharacterFilterEntity(id = viewModel.defaultFilterId ?: UUID.randomUUID())
            filters.forEach {
                filterEntity = when (it.type) {
                    CharacterFilterType.Gender -> filterEntity.copy(gender = it.selectedFilter)
                    CharacterFilterType.Species -> filterEntity.copy(species = it.selectedFilter)
                    CharacterFilterType.Status -> filterEntity.copy(status = it.selectedFilter)
                }
            }
            viewModel.insertCharacterFilter(
                filterEntity
            )
            onCheckMarkClick(viewModel.filters)
            navigateUp()
        },
        onFilterOptionClick = {
            viewModel.selectedFilterType = it.type
            onFilterOptionClick(it)
        },
        filters = filters,
        enableCheckMark = viewModel.enableCheckMark,
        enableClearFilters = viewModel.enableClearFilters,
        onClearFiltersClick = {
            viewModel.filters = CharacterFilterDataSource.getFilters(context = context)
            viewModel.selectedFilterType = null
            viewModel.enableClearFilters = false
            viewModel.enableCheckMark = true
        },
    )
}

@Composable
private fun CharacterFilterOptionsScreenContent(
    enableClearFilters: Boolean,
    enableCheckMark: Boolean,
    filters: List<CharacterFilter<*>>,
    onCheckMarkClick: () -> Unit,
    onClearFiltersClick: () -> Unit,
    onFilterOptionClick: (CharacterFilter<*>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FiltersTopBar(
            enableClearFilters = enableClearFilters,
            enableCheckMark = enableCheckMark,
            onBackButtonClick = {},
            onCheckMarkClick = onCheckMarkClick,
            onClearFiltersClick = onClearFiltersClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            filters.forEach { filter ->
                FilterOptionItem(
                    title = stringResource(filter.type.titleResId),
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
    enableClearFilters: Boolean,
    enableCheckMark: Boolean,
    onBackButtonClick: () -> Unit,
    onCheckMarkClick: () -> Unit,
    onClearFiltersClick: () -> Unit,
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
                RickAndMortyBackButton(onClick = onBackButtonClick)

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Center)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = null,
                        tint = if (enableClearFilters) MaterialTheme.rickAndMortyColors.icon.primary else MaterialTheme.rickAndMortyColors.text.gray,
                        modifier = Modifier
                            .noRippleClickable {
                                if (enableClearFilters) {
                                    onClearFiltersClick()
                                }
                            }
                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = if (enableCheckMark) MaterialTheme.rickAndMortyColors.icon.primary else MaterialTheme.rickAndMortyColors.text.gray,
                        modifier = Modifier
                            .noRippleClickable {
                                if (enableCheckMark) {
                                    onCheckMarkClick()
                                }
                            }
                    )
                }

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
    CompositionLocalProvider(LocalNavController provides rememberNavController()) {
        RickAndMortyGraphQLTheme {
            CharacterFilterOptionsScreenContent(
                enableCheckMark = false,
                onCheckMarkClick = {},
                onFilterOptionClick = {},
                filters = CharacterFilterDataSource.getFilters(LocalContext.current),
                enableClearFilters = false,
                onClearFiltersClick = {},
            )
        }
    }
}