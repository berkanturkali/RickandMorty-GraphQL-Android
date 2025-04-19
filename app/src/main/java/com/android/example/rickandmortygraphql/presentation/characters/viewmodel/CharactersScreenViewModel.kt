package com.android.example.rickandmortygraphql.presentation.characters.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.rickandmortygraphql.data.abstraction.CharacterClient
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(
    private val characterClient: CharacterClient
) : ViewModel() {

    var characters = mutableStateOf(emptyList<SimpleCharacter>())
        private set

    init {
        fetchCharacters()
    }

    fun fetchCharacters(page: Int = 1) {
        viewModelScope.launch {
            try {
                characters.value = characterClient.getCharacters(page)
                characters.value.forEach {
                    Timber.d("Character: ${it.name}, ${it.image}")
                }
            } catch (e: Exception) {
                Timber.e("Error fetching characters: ${e.message}")
                e.printStackTrace()
            }
        }
    }

}