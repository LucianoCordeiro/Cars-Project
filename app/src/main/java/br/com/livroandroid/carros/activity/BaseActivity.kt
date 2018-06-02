package br.com.livroandroid.carros.activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")

open class BaseActivity : AppCompatActivity() {
    //Property to access the context from anywhere
    protected val context: Context get() = this
    //Common methods for all activities go here
}


