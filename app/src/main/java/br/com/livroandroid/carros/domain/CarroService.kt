package br.com.livroandroid.carros.domain

import android.content.Context
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.R.string.carros
import br.com.livroandroid.carros.extensions.fromJson
import org.json.JSONArray
import org.json.JSONObject

object CarroService {

    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
        val raw = getArquivoRaw(tipo)
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use {
            val json = it.readText()
            val carros = fromJson<List<Carro>>(json)
            return carros
        }

    }

    fun getArquivoRaw(tipo: TipoCarro) = when(tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        TipoCarro.luxo -> R.raw.carros_luxo
    }

    private fun parserJson(json: String): List<Carro> {
        val carros = mutableListOf<Carro>()
        val obj = JSONObject(json)
        val array = obj.getJSONArray("carro")
        for (i in 0..array.length() - 1){
            val jsonCarro = array.getJSONObject(i)
            val c = Carro()

            c.nome = jsonCarro.optString("nome")
            c.desc = jsonCarro.optString("desc")
            c.urlFoto = jsonCarro.optString("url_foto")
            carros.add(c)
        }
        return carros
    }
}