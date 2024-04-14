package com.re.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.re.pokemon.model.PokemonListResponse
import com.re.pokemon.model.PokemonResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    val pokemonsList = MutableLiveData<List<PokemonResponseItem>>()

    val LIMIT = 20

    fun getPokemons(offset: Int){
        Api.getPokemonService().getPokemons(offset, LIMIT).enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                if (response.isSuccessful) {
                    val pokemonListResponse = response.body()
                    pokemonListResponse?.results?.let { pokemonsList.postValue(it) }
                } else {
                    // handle error

                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                // handle error
            }
        })
    }

}