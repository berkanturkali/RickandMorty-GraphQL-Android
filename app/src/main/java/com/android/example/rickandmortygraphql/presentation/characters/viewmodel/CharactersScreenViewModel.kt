package com.android.example.rickandmortygraphql.presentation.characters.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.rickandmortygraphql.data.abstraction.CharacterClient
import com.android.example.rickandmortygraphql.data.cache.entity.CharacterFilterEntity
import com.android.example.rickandmortygraphql.data.repository.FilterRepository
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(
    private val characterClient: CharacterClient,
    private val filterRepository: FilterRepository,
) : ViewModel() {

    companion object {
        private const val SEARCH_QUERY_DEBOUNCE_DURATION = 300L
    }

    var searchQuery by mutableStateOf("")

    var characters = mutableStateOf(emptyList<SimpleCharacter>())
        private set

    var showBadgeOnFiltersIcon by mutableStateOf(false)

    private val filters = MutableStateFlow<CharacterFilterEntity?>(null)

    private val _searchQuery: Flow<String?>
        get() =
            snapshotFlow {
                searchQuery
            }

                .map {
                    it.ifBlank { null }
                }
                .debounce(SEARCH_QUERY_DEBOUNCE_DURATION)
                .distinctUntilChanged()


    init {
        fetchFilters()
        viewModelScope.launch {
            (combine(
                filters,
                _searchQuery,
                ::Pair
            ).collect { (filters, name) ->
                fetchCharacters(
                    name,
                    filters
                )
            })
        }
    }

    fun fetchCharacters(name: String?, characterFilters: CharacterFilterEntity?, page: Int = 1) {
        viewModelScope.launch {
            try {
                characters.value = characterClient.getCharacters(name, characterFilters, page)
            } catch (e: Exception) {
                Timber.e("Error fetching characters: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    private fun fetchFilters() {
        viewModelScope.launch {
            filterRepository.getCharacterFilters().collect { entity ->
                filters.update {
                    entity
                }
            }
        }
    }

}