package com.android.example.rickandmortygraphql.presentation.locations.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.example.rickandmortygraphql.model.Location
import com.android.example.rickandmortygraphql.presentation.characters.screen.LIST_ITEM_ANIM_DURATION
import com.android.example.rickandmortygraphql.presentation.characters.screen.LIST_ITEM_INITIAL_ANIM_VALUE
import com.android.example.rickandmortygraphql.presentation.locations.components.LocationItem
import com.android.example.rickandmortygraphql.presentation.locations.viewmodel.LocationsScreenViewModel
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationsScreenViewModel = hiltViewModel()
) {

    val locations = viewModel.locations.value
    val isLoading = viewModel.showLoading

    LocationsScreenContent(
        modifier = modifier,
        isLoading = isLoading,
        locations = locations,
    )
}

@Composable
fun LocationsScreenContent(
    isLoading: Boolean,
    locations: List<Location>,
    modifier: Modifier = Modifier
) {

    val listState = rememberLazyListState()

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = MaterialTheme.rickAndMortyColors.text.primary
            )
        }
    } else {
        LazyColumn(
            state = listState,
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        ) {
            items(locations.size) { index ->
                val initialValue = if (listState.lastScrolledForward) {
                    LIST_ITEM_INITIAL_ANIM_VALUE
                } else {
                    -LIST_ITEM_INITIAL_ANIM_VALUE
                }
                val animatedProgress =
                    remember { Animatable(initialValue = initialValue.toFloat()) }
                LaunchedEffect(Unit) {
                    animatedProgress.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            LIST_ITEM_ANIM_DURATION,
                            easing = LinearEasing
                        )
                    )
                }
                val location = locations[index]
                LocationItem(
                    location = location.name,
                    type = location.type,
                    modifier = Modifier
                        .graphicsLayer(translationY = animatedProgress.value)
                )
            }
        }
    }
}