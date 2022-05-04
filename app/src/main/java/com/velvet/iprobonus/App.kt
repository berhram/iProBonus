package com.velvet.iprobonus

import android.app.Application
import com.velvet.data.di.DataModule

class App : Application() {
    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().dataModule(DataModule(this)).build()
    }
}