package com.re.pokemon

import com.re.pokemon.model.Pokemon
import com.re.pokemon.model.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<PokemonListResponse>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id")id: Int): Call<Pokemon>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>


}