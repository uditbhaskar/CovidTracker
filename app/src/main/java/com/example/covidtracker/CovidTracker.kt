package com.example.covidtracker

import android.app.Application
import com.example.covidtracker.di.component.ApplicationComponent
import com.example.covidtracker.di.component.DaggerApplicationComponent
import com.example.covidtracker.di.module.ApplicationModule
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration



lateinit var covidApp: App

val appID : String = "covidtracker-tohmp";


class CovidTracker : Application() {

    companion object {
        public const val TAG = "Covid Tracker"
    }

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        injectDependencies()
        initializeRealm()
    }

    private fun initializeRealm() {
        Realm.init(this)
        covidApp = App(AppConfiguration.Builder(appID)
            .build())
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)


    }


}