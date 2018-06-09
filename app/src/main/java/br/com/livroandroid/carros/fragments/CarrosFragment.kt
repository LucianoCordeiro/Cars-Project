package br.com.livroandroid.carros.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro


class CarrosFragment : Fragment() {
    private var tipo: TipoCarro = TipoCarro.classicos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipo = arguments?.getSerializable("tipo") as TipoCarro
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_carros, container, false)
        val textView = view?.findViewById<TextView>(R.id.text)

        val tipoString = getString(tipo.string)
        textView?.text = "Carros $tipoString"
        return view

    }
}


