package br.com.livroandroid.carros.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.adapter.TabsAdapter
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.setupToolbar
import br.com.livroandroid.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
        setupNavDrawer()
        setupViewPagerTabs()
    }

    private fun setupViewPagerTabs() {
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)
    }

    //Configure the Navigation Drawer
    private fun setupNavDrawer() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    // Trata o evento do Navigation Drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                val intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", TipoCarro.classicos)
                startActivity(intent)
            }
            R.id.nav_item_carros_esportivos -> {
                val intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", TipoCarro.esportivos)
                startActivity(intent)
            }
            R.id.nav_item_carros_luxo -> {
                val intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", TipoCarro.luxo)
                startActivity(intent)
            }
            R.id.nav_item_site_livro -> {
                val intent = Intent(context, SiteLivroActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_settings -> {
                toast("Clicou em configurações")
            }
        }
        // Fecha o menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
