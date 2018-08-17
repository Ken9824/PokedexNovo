package br.com.fiap.pokedex.model

import android.graphics.drawable.Drawable

data class Pokemon(val numero: Int,
                   val nome: String,
                   val tipo: String,
                   val descricao: String,
                   val imagem: Drawable)