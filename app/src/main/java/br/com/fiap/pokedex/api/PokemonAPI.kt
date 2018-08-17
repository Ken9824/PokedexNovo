package br.com.fiap.pokedex.api

import br.com.fiap.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PokemonAPI{
    @GET("/api/pokemon")
    fun todosPokemons(@Query("size") size: Int) : Call<PokemonResponse>
}