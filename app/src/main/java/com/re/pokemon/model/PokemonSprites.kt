package com.re.pokemon.model

import com.google.gson.annotations.SerializedName

/**
 * This class represents the sprites images of a pokemon
 */
data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String
) {

}
