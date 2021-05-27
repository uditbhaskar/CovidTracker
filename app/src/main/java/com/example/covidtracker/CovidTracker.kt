package com.example.covidtracker

import android.app.Application
import com.example.covidtracker.di.component.ApplicationComponent
import com.example.covidtracker.di.component.DaggerApplicationComponent
import com.example.covidtracker.di.module.ApplicationModule

class CovidTracker: Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}