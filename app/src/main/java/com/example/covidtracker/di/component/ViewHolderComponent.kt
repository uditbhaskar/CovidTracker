package com.example.covidtracker.di.component

import com.example.covidtracker.di.component.ApplicationComponent
import com.example.covidtracker.di.ViewModelScope
import com.example.covidtracker.di.module.ViewHolderModule


import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {


}