package com.example.covidtracker.di.component


import com.example.covidtracker.di.ActivityScope
import com.example.covidtracker.di.module.ActivityModule
import com.example.covidtracker.ui.home.HomeActivity
import com.example.covidtracker.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: HomeActivity)

}