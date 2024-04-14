package com.re.pokemon.model

/**
 * This class represents a single detailed pokemon
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites,
) {

}
