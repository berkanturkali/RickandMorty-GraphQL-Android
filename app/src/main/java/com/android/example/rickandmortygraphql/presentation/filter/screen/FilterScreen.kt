package com.android.example.rickandmortygraphql.presentation.filter.screen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.rickandmortygraphql.R
import com.android.example.rickandmortygraphql.ui.theme.RickAndMortyGraphQLTheme
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors
import com.android.example.rickandmortygraphql.utils.noRippleClickable

@Composable
fun FilterScreen(
    title: String,
    previouslySelectedFilter: String?,
    filters: List<String>,
    onBackButtonClick: () -> Unit,
    onCheckMarkClick: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {

    FilterScreenContent(
        title = title,
        previouslySelectedFilter = previouslySelectedFilter,
        filters = filters,
        onBackButtonClick = onBackButtonClick,
        onCheckMarkClick = onCheckMarkClick,
        modifier = modifier
    )
}

@Composable
fun FilterScreenContent(
    title: String,
    previouslySelectedFilter: String?,
    filters: List<String>,
    onBackButtonClick: () -> Unit,
    onCheckMarkClick: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {

    var selectedFilter by rememberSaveable {
        mutableStateOf(previouslySelectedFilter)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        FiltersTopBar(
            title = title,
            enableCheckMark = selectedFilter != previouslySelectedFilter,
            onBackButtonClick = onBackButtonClick,
            onCheckMarkClick = {
                onCheckMarkClick(selectedFilter)
            },
            onClearFiltersClick = {},
            enableClearFilters = false,
            showClearFiltersButton = false
        )

        filters.forEach { filter ->
            FilterItem(
                value = filter,
                isSelected = selectedFilter == filter,
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClickable {
                        selectedFilter = if (selectedFilter == filter) {
                            null
                        } else {
                            filter
                        }
                    }
            )
        }
    }
}

@Composable
private fun FilterItem(
    value: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {

    val transition = updateTransition(targetState = isSelected, label = "")

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 400)
            } else {
                tween(durationMillis = 250)
            }
        },
        label = ""
    ) { state ->
        if (state) 1f else 0f
    }

    val scale by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 400)
            } else {
                tween(durationMillis = 250)
            }
        },
        label = ""
    ) { state ->
        if (state) 1f else 0f
    }

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.rickAndMortyColors.text.primary
            )

            Icon(
                painterResource(R.drawable.ic_round_check),
                contentDescription = null,
                tint = MaterialTheme.rickAndMortyColors.icon.primary,
                modifier = Modifier
                    .size(20.dp)
                    .alpha(alpha)
                    .scale(scale)
            )
        }
    }
}

@Composable
@Preview
private fun FilterScreenContentPrev() {
    RickAndMortyGraphQLTheme {
        FilterScreenContent(
            title = "Status",
            previouslySelectedFilter = "Alive",
            filters = listOf("Alive", "Dead", "Unknown"),
            onBackButtonClick = {},
            onCheckMarkClick = {},
        )
    }
}