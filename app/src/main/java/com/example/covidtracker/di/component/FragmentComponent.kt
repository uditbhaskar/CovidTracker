package com.example.covidtracker.di.component

import com.example.covidtracker.di.component.ApplicationComponent
import com.example.covidtracker.di.FragmentScope
import com.example.covidtracker.di.module.FragmentModule

import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {


}