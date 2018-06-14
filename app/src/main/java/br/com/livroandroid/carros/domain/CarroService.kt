package br.com.livroandroid.carros.domain

import android.content.Context

object CarroService {

    fun getCarros(context: Context?, tipo: TipoCarro): List<Carro> {
        val tipoString = context?.getString(tipo.string)

        val carros = mutableListOf<Carro>()

        for (i in 1..20) {
            val c = Carro()

            c.nome = "Carro $tipoString: $i"
            c.desc = "Desc $i"
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png"
            carros.add(c)
        }
        return carros
    }
}