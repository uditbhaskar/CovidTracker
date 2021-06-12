package com.example.covidtracker.ui.home.searchList


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import com.example.covidtracker.data.repository.SearchedCountryDataRepository
import com.example.covidtracker.ui.base.BaseItemViewModel
import com.example.covidtracker.utils.common.Event
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

    companion object {
        const val TAG = "SearchItemViewModel"
    }

    val countryName: LiveData<String> = Transformations.map(data) { it.Country }
    val provinceName: LiveData<String?> = Transformations.map(data) { it.Province }
    val confirmedCount: LiveData<Int> = Transformations.map(data) { it.Confirmed }
    val activeCount: LiveData<Int> = Transformations.map(data) { it.Active }
    val deathCount: LiveData<Int> = Transformations.map(data) { it.Deaths }
    val recoveredCount: LiveData<Int> = Transformations.map(data) { it.Recovered }

    val onLaunchDetailsActivity: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    override fun onCreate() {

    }


    fun saveItemInDatabase(
        countryName: String?,
        provinceName: String?,
        confirmedCount: String?,
        deathCount: String?,
        recoveredCount: String?,
        activeCount: String?
    ) {
        searchedCountriesDataRepository.saveItemsInDB(
            countryName,
            provinceName,
            confirmedCount,
            deathCount,
            recoveredCount,
            activeCount
        )
    }

    fun onLaunchDetailsView() {
        onLaunchDetailsActivity.postValue(Event(emptyMap()))
    }

}