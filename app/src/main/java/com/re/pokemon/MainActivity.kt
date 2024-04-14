package com.re.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.re.pokemon.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainActivityViewModel by viewModels()
    var pokemonAdapter : PokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchEditText.addTextChangedListener {
            //TODO: implement search
        }

        viewModel.getPokemons(0)
        viewModel.pokemonsList.observe(this) {
            pokemonAdapter?.updateData(it ?: emptyList())
        }

        pokemonAdapter = PokemonAdapter(this, viewModel.pokemonsList.value ?: emptyList())
        binding.pokemonRecyclerView.adapter = pokemonAdapter
        binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(this)

    }
}