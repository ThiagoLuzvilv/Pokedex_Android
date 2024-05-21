package com.example.k.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.k.R
import com.example.k.domain.Pokemon

class PokemonAdapter(private var items: List<Pokemon?>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(),
    Filterable {

    private var filteredItems: List<Pokemon?> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filteredItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredItems[position]
        holder.bindView(item)
    }



    fun setItems(pokemons: List<Pokemon?>) {
        items = pokemons
        filteredItems = pokemons
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
        private val tvNumero: TextView = itemView.findViewById(R.id.tvNumero)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvType1: TextView = itemView.findViewById(R.id.tvType1)
        private val tvType2: TextView = itemView.findViewById(R.id.tvType2)

        fun bindView(item: Pokemon?) {
            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)
                tvNumero.text = "N ${it.formattedNumber}"
                tvName.text = it.formattedName
                tvType1.text = it.types[0].name.capitalize()

                if (it.types.size > 1) {
                    tvType2.visibility = View.VISIBLE
                    tvType2.text = it.types[1].name.capitalize()
                } else {
                    tvType2.visibility = View.GONE
                }
            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Pokemon?>()
                if (constraint.isNullOrEmpty()) {
                    filteredList.addAll(items)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (pokemon in items) {
                        if (pokemon?.formattedName?.toLowerCase()?.contains(filterPattern) == true) {
                            filteredList.add(pokemon)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = results?.values as? List<Pokemon?> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}
