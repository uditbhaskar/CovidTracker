package com.example.covidtracker.di.component

import com.example.covidtracker.di.component.ApplicationComponent
import com.example.covidtracker.di.ViewModelScope
import com.example.covidtracker.di.module.ViewHolderModule
import com.example.covidtracker.ui.home.historyList.HistoryItemViewHolder
import com.example.covidtracker.ui.home.searchList.SearchItemViewHolder


import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {
    fun inject(itemViewHolder: SearchItemViewHolder)
    fun inject(itemViewHolder: HistoryItemViewHolder)


}