package com.android.example.rickandmortygraphql.presentation.episodes.screen

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
import com.android.example.rickandmortygraphql.model.Episode
import com.android.example.rickandmortygraphql.presentation.characters.screen.LIST_ITEM_ANIM_DURATION
import com.android.example.rickandmortygraphql.presentation.characters.screen.LIST_ITEM_INITIAL_ANIM_VALUE
import com.android.example.rickandmortygraphql.presentation.episodes.components.EpisodeItem
import com.android.example.rickandmortygraphql.presentation.episodes.viewmodel.EpisodesScreenViewModel
import com.android.example.rickandmortygraphql.ui.theme.rickAndMortyColors

@Composable
fun EpisodesScreen(
    modifier: Modifier = Modifier,
    viewModel: EpisodesScreenViewModel = hiltViewModel(),
) {
    val episodes = viewModel.episodes.value
    val isLoading = viewModel.showLoading

    EpisodesScreenContent(
        isLoading = isLoading,
        episodes = episodes,
        modifier = modifier
    )
}

@Composable
fun EpisodesScreenContent(
    isLoading: Boolean,
    episodes: List<Episode>,
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
            items(episodes.size) { index ->
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
                val episode = episodes[index]
                EpisodeItem(
                    name = episode.name ?: "",
                    date = episode.airDate ?: "",
                    episode = episode.episode ?: "",
                    modifier = Modifier
                        .graphicsLayer {
                            translationY = animatedProgress.value
                        }
                )
            }
        }
    }

}