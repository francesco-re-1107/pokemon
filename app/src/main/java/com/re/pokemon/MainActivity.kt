package com.re.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.re.pokemon.databinding.ActivityMainBinding
import com.re.pokemon.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokemonRecyclerView.adapter = PokemonAdapter()

        Api.getPokemonService().getPokemon(1).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    Log.d("Pokemon", pokemon.toString())
                }else{
                    Log.e("Pokemon", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.e("Pokemon", "Error: ${t.message}")
            }
        })
    }
}