package com.example.covidtracker.ui.home.searchList

import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import com.example.covidtracker.data.repository.SearchedCountryDataRepository
import com.example.covidtracker.ui.base.BaseItemViewModel
import com.example.covidtracker.utils.network.NetworkHelper
import com.example.covidtracker.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val searchedCountriesDataRepository: SearchedCountryDataRepository

) : BaseItemViewModel<SearchedCountryDataResponse>(
    schedulerProvider,
    compositeDisposable,
    networkHelper
) {

    override fun onCreate() {
    }

    fun saveItemInDatabase() {
    }

    fun onLaunch() {
        //going to detail page activity
    }

}