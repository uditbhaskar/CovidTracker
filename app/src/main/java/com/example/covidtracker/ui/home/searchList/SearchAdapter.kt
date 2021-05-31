package com.example.covidtracker.ui.home.searchList

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import com.example.covidtracker.ui.base.BaseAdapter

class SearchAdapter(
    parentLifecycle: Lifecycle,
    searchItems : ArrayList<SearchedCountryDataResponse>

): BaseAdapter<SearchedCountryDataResponse, SearchItemViewHolder>(parentLifecycle,searchItems){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder = SearchItemViewHolder(parent)
}
