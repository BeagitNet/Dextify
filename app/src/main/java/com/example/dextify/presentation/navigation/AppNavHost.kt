package com.example.dextify.presentation.navigation

/*
 * AppNavHost
 * ----------
 * Main navigation graph of Dextify.
 * Handles Loading → Pokemon List → Pokemon Detail flow.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.dextify.presentation.screen.pokemon_list.PokemonListScreen
import com.example.dextify.presentation.screen.pokemon_list.PokemonListViewModel
import com.example.dextify.presentation.loadingscreen.LoadingScreen  // ← add this

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.LOADING  // ← changed from POKEMON_LIST
    ) {

        // ← add this entire block
        composable(Destinations.LOADING) {
            LoadingScreen(
                onAnimationFinished = {
                    navController.navigate(Destinations.POKEMON_LIST) {
                        popUpTo(Destinations.LOADING) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.POKEMON_LIST) {
            val viewModel: PokemonListViewModel = hiltViewModel()
            val pokemons by viewModel.pokemonList.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                PokemonListScreen(
                    pokemons = pokemons,
                    onPokemonClick = { id ->
                        navController.navigate("${Destinations.POKEMON_DETAIL}/$id")
                    }
                )
            }
        }

        composable(Destinations.POKEMON_DETAIL_ROUTE) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(Destinations.ARG_POKEMON_ID)
            PokemonDetailScreen(pokemonId = id ?: "Unknown")
        }
    }
}

@Composable
fun PokemonDetailScreen(pokemonId: String?) {
    TODO("Not yet implemented")
}

