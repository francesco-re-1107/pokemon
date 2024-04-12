package com.re.pokemon

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    const val BASE_URL = "https://pokeapi.co/api/v2/"

    private var pokemonService: PokemonService? = null

    fun getPokemonService(): PokemonService {
        if (pokemonService != null)
            return pokemonService!!

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

         return retrofit.create(PokemonService::class.java)
    }
}