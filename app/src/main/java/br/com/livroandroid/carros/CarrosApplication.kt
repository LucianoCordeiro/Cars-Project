package br.com.livroandroid.carros
import android.app.Application
import android.util.Log


class CarrosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Save the instance so that we can access it as Singleton
        appInstance = this
    }
    companion object {
        // class Application Singleton
        private var appInstance: CarrosApplication? = null
        fun getInstance(): CarrosApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure the class Application in AndroidManifest.xml")
            }
            return appInstance!!
        }
    }
}