package com.example.dextify.presentation.screen.pokemon_list

/*
 * PokemonListScreen
 * ------------------
 * Displays the list of pokemons in a scrollable layout.
 * Each item is clickable and triggers navigation via callback.
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun PokemonListScreen(
    pokemons: List<String>,
    onPokemonClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonListItem(name = pokemon, onClick = onPokemonClick)
        }
    }
}

@Composable
fun PokemonListItem(
    name: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick(name) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // Image slot (placeholder for pokemon sprite)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = name.replaceFirstChar { it.uppercase(Locale.getDefault()) },
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}