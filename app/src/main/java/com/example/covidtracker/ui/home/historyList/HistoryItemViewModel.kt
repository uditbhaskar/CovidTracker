package com.example.covidtracker.ui.home.historyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.covidtracker.data.local.entity.SavedItemEntity
import com.example.covidtracker.data.repository.SearchedCountryDataRepository
import com.example.covidtracker.ui.base.BaseItemViewModel
import com.example.covidtracker.utils.common.Event
import com.example.covidtracker.utils.network.NetworkHelper
import com.example.covidtracker.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HistoryItemViewModel  @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val searchedCountriesDataRepository: SearchedCountryDataRepository

) : BaseItemViewModel<SavedItemEntity>(schedulerProvider, compositeDisposable, networkHelper) {


    val countryName: LiveData<String> = Transformations.map(data){ it.Country }
    val provinceName : LiveData<String?> = Transformations.map(data) {it.Province}
    val confirmedCount : LiveData<String> = Transformations.map(data){it.Confirmed}
    val activeCount : LiveData<String> = Transformations.map(data){it.Active}
    val deathCount : LiveData<String> = Transformations.map(data){it.Deaths}
    val recoveredCount : LiveData<String> = Transformations.map(data){it.Recovered}

    val onLaunchDetailsActivity : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {

    }

    fun onLaunchDetailsView() {
        onLaunchDetailsActivity.postValue(Event(emptyMap()))
    }

}