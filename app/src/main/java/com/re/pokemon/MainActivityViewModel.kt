package com.re.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.re.pokemon.model.PokemonListResponse
import com.re.pokemon.model.PokemonListResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel(), Callback<PokemonListResponse> {

    val pokemonsList = MutableLiveData<List<PokemonListResponseItem>>(emptyList())
    var isFinishedPages = false
    val isLoading = MutableLiveData(false)

    val LIMIT = 20

    /**
     * Load another page of pokemons from the API into the list
     */
    fun loadMorePokemons() {
        val currentList = pokemonsList.value ?: emptyList()
        Api.getPokemonService().getPokemons(currentList.size, LIMIT).enqueue(this)
        isLoading.postValue(true)
    }

    /**
     * Callback for the API call to get the list of pokemons
     */
    override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
        isLoading.postValue(false)
        if (response.isSuccessful) {
            val pokemonListResponse = response.body()

            pokemonListResponse?.results?.let {
                val updatedList = pokemonsList.value?.toMutableList() ?: mutableListOf()
                updatedList.addAll(it)

                isFinishedPages = pokemonListResponse.count == updatedList.size
                pokemonsList.postValue(updatedList)
            }
        } else {
            // TODO: handle error
        }
    }

    /**
     * Callback for the API call to get the list of pokemons when it fails
     */
    override fun onFailure(p0: Call<PokemonListResponse>, p1: Throwable) {
        // TODO: handle error
        isLoading.postValue(false)
    }

}