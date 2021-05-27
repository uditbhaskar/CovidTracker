package com.example.covidtracker.di.component


import com.example.covidtracker.di.ActivityScope
import com.example.covidtracker.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

}