package com.re.pokemon.model

import com.re.pokemon.Api

class PokemonResponseItem(
    val name: String,
    val url: String
) {

    var imageUrl = ""
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        private set

    var id = ""
        get() = url.split("/").last { it.isNotEmpty() } // take last non empty path parameter
        private set

}
