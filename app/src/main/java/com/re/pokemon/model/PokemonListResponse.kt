package com.re.pokemon.model

/**
 * This class represents the response from the API when requesting a list of pokemons
 */
data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonListResponseItem>
) {
}
