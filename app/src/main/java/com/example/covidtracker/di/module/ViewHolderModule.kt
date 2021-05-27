package com.example.covidtracker.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.covidtracker.di.ViewModelScope
import com.example.covidtracker.ui.base.BaseItemViewHolder

import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}