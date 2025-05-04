package com.android.example.rickandmortygraphql.presentation.locations.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.rickandmortygraphql.data.abstraction.LocationClient
import com.android.example.rickandmortygraphql.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LocationsScreenViewModel @Inject constructor(
    private val locationClient: LocationClient
) : ViewModel() {

    var locations = mutableStateOf(emptyList<Location>())
        private set

    var showLoading by mutableStateOf(false)

    init {
        getLocations()
    }

    private fun getLocations(page: Int = 1) {
        showLoading = true
        viewModelScope.launch {
            try {
                locations.value = locationClient.getLocations(page)
            } catch (e: Exception) {
                Timber.e("Error fetching locations: ${e.message}")
                e.printStackTrace()
            } finally {
                showLoading = false
            }
        }
    }
}