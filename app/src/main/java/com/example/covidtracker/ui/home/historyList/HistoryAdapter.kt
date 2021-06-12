package com.example.covidtracker.ui.home.historyList

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.covidtracker.data.mongodb.realm_object_model.CovidData
import com.example.covidtracker.ui.base.BaseAdapter


class HistoryAdapter(
    parentLifecycle: Lifecycle,
    savedItem: ArrayList<CovidData>
) : BaseAdapter<CovidData, HistoryItemViewHolder>(parentLifecycle, savedItem) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder =
        HistoryItemViewHolder(parent)
}