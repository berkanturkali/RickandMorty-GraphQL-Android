package com.android.example.rickandmortygraphql.presentation.filter.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import com.android.example.rickandmortygraphql.data.repository.FilterRepository
import com.android.example.rickandmortygraphql.model.CharacterFilter
import com.android.example.rickandmortygraphql.model.CharacterFilterDataSource
import com.android.example.rickandmortygraphql.model.CharacterFilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CharacterFilterViewModel @Inject constructor(
    private val application: Application,
    private val filterRepository: FilterRepository
) : AndroidViewModel(application) {

    private var initialFilters: List<CharacterFilter<*>> = emptyList()

    var filters by mutableStateOf<List<CharacterFilter<*>>>(emptyList())

    var selectedFilterType: CharacterFilterType? = null

    var enableCheckMark by mutableStateOf(false)

    var defaultFilterId: UUID? = null

    init {
        getCharacterFilters()
    }

    private fun getCharacterFilters() {
        viewModelScope.launch {
            val defaultFilters = filterRepository.getCharacterFilters().firstOrNull()
            filters = if (defaultFilters != null) {
                defaultFilterId = defaultFilters.id
                CharacterFilterDataSource.getFilters(application.applicationContext).map { filter ->
                    val selectedFilter = getSelectedFilterByType(filter, defaultFilters)
                    filter.copy(selectedFilter = selectedFilter)
                }
            } else {
                CharacterFilterDataSource.getFilters(application.applicationContext)
            }
            initialFilters = filters
        }
    }

    fun insertCharacterFilter(characterFilter: CharacterFilterEntity) {
        viewModelScope.launch {
            filterRepository.insertCharacterFilter(characterFilter)
        }
    }

    private fun getSelectedFilterByType(
        filter: CharacterFilter<*>,
        defaultFilters: CharacterFilterEntity,
    ): String? {
        return when (filter.type) {
            CharacterFilterType.Gender -> defaultFilters.gender
            CharacterFilterType.Species -> defaultFilters.species
            CharacterFilterType.Status -> defaultFilters.status
        }
    }

    fun setSelectedFilter(filter: String?) {
        val modifiedFilterIndex = filters.indexOfFirst {
            selectedFilterType == it.type
        }
        if (modifiedFilterIndex != -1) {
            val newList = filters.toMutableList()
            val modifiedItem = filters[modifiedFilterIndex].copy(selectedFilter = filter)
            newList[modifiedFilterIndex] = modifiedItem

            filters = newList
            enableCheckMark = filters != initialFilters
        }
    }
}