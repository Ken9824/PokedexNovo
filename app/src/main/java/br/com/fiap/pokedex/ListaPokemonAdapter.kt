package br.com.fiap.pokedex

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_item.view.*

class ListaPokemonAdapter(private val pokemons: List<Pokemon>,
                          private val context: Context,
                          val listener: (Pokemon) -> Unit) : Adapter<ListaPokemonAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder?.let {
            it.bindView(pokemon, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: Pokemon,
                     listener: (Pokemon) -> Unit) = with(itemView) {

            ivDbz.setImageDrawable(pokemon.imagem)
            tvNome.text = pokemon.nome
            tvTipo.text = pokemon.tipo
            tvDescricao.text = pokemon.descricao

            setOnClickListener { listener(pokemon) }
        }
    }
}