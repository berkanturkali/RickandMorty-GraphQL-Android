package com.android.example.rickandmortygraphql.presentation.episodes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.rickandmortygraphql.data.abstraction.EpisodeClient
import com.android.example.rickandmortygraphql.model.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EpisodesScreenViewModel @Inject constructor(
    private val episodeClient: EpisodeClient
) : ViewModel() {

    var episodes = mutableStateOf(emptyList<Episode>())
        private set

    var showLoading by mutableStateOf(false)

    init {
        getEpisodes()
    }

    private fun getEpisodes(page: Int = 1) {
        showLoading = true
        viewModelScope.launch {
            try {
                episodes.value = episodeClient.getEpisodes(page)
            } catch (e: Exception) {
                Timber.e("Error fetching episodes: ${e.message}")
                e.printStackTrace()
            } finally {
                showLoading = false
            }
        }
    }

}