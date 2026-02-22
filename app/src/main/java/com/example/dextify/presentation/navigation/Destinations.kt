package com.example.dextify.presentation.navigation
/*
 * Destinations
 * ------------
 * Central route definitions for app navigation.
 * Keeps all screen routes and arguments in one place.
 */

object Destinations {
    const val LOADING = "loading"
    const val POKEMON_LIST = "pokemon_list"
    const val POKEMON_DETAIL = "pokemon_detail"
    const val ARG_POKEMON_ID = "pokemonId"
    const val POKEMON_DETAIL_ROUTE = "$POKEMON_DETAIL/{$ARG_POKEMON_ID}"
}