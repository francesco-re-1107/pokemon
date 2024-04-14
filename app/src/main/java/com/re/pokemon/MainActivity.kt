package com.re.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        //load initial pokemons
        viewModel.loadMorePokemons()

        setupObservers()
        setupRecyclerView()
    }

    /**
     * Setup the observers from the view model to update the UI
     */
    private fun setupObservers() {
        binding.searchEditText.addTextChangedListener {
            //TODO: implement search
        }

        viewModel.pokemonsList.observe(this) {
            pokemonAdapter?.updateData(it ?: emptyList())
        }

        viewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    /**
     * Setup the recycler view with the adapter, the layout manager and the scroll listener
     */
    private fun setupRecyclerView() {
        pokemonAdapter = PokemonAdapter(this, viewModel.pokemonsList.value ?: emptyList())
        binding.pokemonRecyclerView.adapter = pokemonAdapter
        binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokemonRecyclerView.addOnScrollListener(object :
            PaginationScrollListener(binding.pokemonRecyclerView.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.loadMorePokemons()
                }

                override fun isLastPage() = viewModel.isFinishedPages

                override fun isLoading() = viewModel.isLoading.value ?: false
        })
    }
}