package com.example.covidtracker.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.ui.base.BaseFragment

import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

}