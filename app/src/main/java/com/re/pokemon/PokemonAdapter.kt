package com.re.pokemon

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.re.pokemon.model.PokemonResponseItem
import com.squareup.picasso.Picasso

class PokemonAdapter(val context: Context, var items: List<PokemonResponseItem>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val v = LayoutInflater.from(context)
            .inflate(R.layout.pokemon_recyclerview_item, parent, false)

        return PokemonViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateData(items: List<PokemonResponseItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonResponseItem) {
            itemView.findViewById<TextView>(R.id.pokemon_name_text_view).text = pokemon.name.replaceFirstChar(Char::uppercase)
            itemView.findViewById<TextView>(R.id.pokemon_description_text_view).text = "placeholder description"

            Picasso.get()
                .load(pokemon.imageUrl)
                .placeholder(R.drawable.pokemon_placeholder)
                .into(itemView.findViewById<ImageView>(R.id.pokemon_image_view))
        }
    }

}
