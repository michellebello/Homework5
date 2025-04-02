package com.example.homework5

import android.app.Application
import com.example.homework5.data.AppContainer
import com.example.homework5.data.DefaultAppContainer

class AmphibianApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}