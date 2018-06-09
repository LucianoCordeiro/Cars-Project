package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.addFragment
import br.com.livroandroid.carros.extensions.setupToolbar
import br.com.livroandroid.carros.fragments.CarrosFragment
import kotlinx.android.synthetic.main.toolbar.*

class CarrosActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)
        setupToolbar(R.id.toolbar, title, true)

        if (savedInstanceState == null) {
            addFragment(R.id.container, CarrosFragment())
        }

    }

}