package com.example.dextify.presentation.screen.pokemon_list


/*
 * PokemonListViewModel
 * ---------------------
 * Handles state for the Pokemon List screen.
 * Manages loading state and provides pokemon data to the UI.
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor() : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<String>>(emptyList())
    val pokemonList: StateFlow<List<String>> = _pokemonList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPokemons()
    }

    // Loads pokemon data (dummy data)
    private fun loadPokemons() {
        viewModelScope.launch {
            delay(700)
            val sample = listOf(
                "Bulbasaur", "Charmander", "Squirtle", "Pikachu", "Eevee"
            )
            _pokemonList.value = sample
            _isLoading.value = false
        }
    }
}