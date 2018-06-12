package br.com.livroandroid.carros.activity

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.dialogs.AboutDialog
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_site_livro.*


class SiteLivroActivity : AppCompatActivity() {
    private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)

        val actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Loads the page
        setWebViewClient(webview)
        webview.loadUrl(URL_SOBRE)

        swipeToRefresh.setOnRefreshListener { webview.reload() }

        swipeToRefresh.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3)
    }

    //Controls the WebView events
    private fun setWebViewClient(webview: WebView?) {
        webview?.webViewClient = object: WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.INVISIBLE
                swipeToRefresh.isRefreshing = false
            }
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) : Boolean {
                val url = request?.url.toString()
                if (url.endsWith("sobre.htm")) {
                    AboutDialog.showAbout(supportFragmentManager)
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}
