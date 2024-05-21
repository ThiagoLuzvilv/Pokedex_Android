package com.example.k.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k.R
import com.example.k.ViewModel.PokemonViewModel
import com.example.k.ViewModel.PokemonViewModelFactory

class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: PokemonAdapter
    private val viewModel: PokemonViewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        // Configurar RecyclerView e adaptador
        recyclerView = findViewById(R.id.rvPokemons)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = PokemonAdapter(emptyList()) // Inicialmente, sem nenhum Pokémon
        recyclerView.adapter = adapter

        // Observar mudanças na lista de pokémons no ViewModel
        viewModel.pokemons.observe(this, Observer { pokemons ->
            adapter.setItems(pokemons) // Atualizar a lista de pokémons no adaptador
        })

        // Configurar SearchView
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText) // Filtrar a lista de acordo com o texto da pesquisa
                return true
            }
        })
    }
}

