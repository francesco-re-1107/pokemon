package com.re.pokemon

import com.re.pokemon.model.Pokemon
import com.re.pokemon.model.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getPokemons(): Call<List<PokemonListResponse>>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>

}