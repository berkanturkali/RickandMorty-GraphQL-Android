package com.android.example.rickandmortygraphql

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.example.rickandmortygraphql.model.RickAndMortyTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel() {

    var theme by mutableStateOf(RickAndMortyTheme.DARK)
}