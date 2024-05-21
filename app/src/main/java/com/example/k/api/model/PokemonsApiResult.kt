package com.example.k.api.model

import com.example.k.domain.PokemonType

data class PokemonsApiResult (
    val count: Int,
    val next: String?,
    val previus: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)


data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)