package com.android.example.rickandmortygraphql.presentation.character.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.rickandmortygraphql.data.abstraction.CharacterClient
import com.android.example.rickandmortygraphql.model.DetailedCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterClient: CharacterClient
) : ViewModel() {

    var characterId: String = checkNotNull(savedStateHandle[ID_ARG_KEY])

    var character by mutableStateOf<DetailedCharacter?>(null)

    var showLoading by mutableStateOf(false)

    companion object {
        private const val ID_ARG_KEY = "id"
    }

    init {
        fetchCharacter(characterId)
    }

    fun fetchCharacter(id: String) {
        showLoading = true
        viewModelScope.launch {
            try {
                character = characterClient.getCharacter(id)
            } catch (e: Exception) {
                Timber.e("Error fetching character: ${e.message}")
                e.printStackTrace()
            } finally {
                showLoading = false
            }
        }
    }
}