package com.example.covidtracker.ui.home.searchList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.covidtracker.data.local.entity.SavedItemEntity
import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import com.example.covidtracker.data.repository.SearchedCountryDataRepository
import com.example.covidtracker.ui.base.BaseItemViewModel
import com.example.covidtracker.utils.common.Event
import com.example.covidtracker.utils.network.NetworkHelper
import com.example.covidtracker.utils.rx.SchedulerProvider
import io.reactivex.Single
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

    val countryName: LiveData<String> = Transformations.map(data) { it.Country }
    val provinceName: LiveData<String?> = Transformations.map(data) { it.Province }
    val confirmedCount: LiveData<Int> = Transformations.map(data) { it.Confirmed }
    val activeCount: LiveData<Int> = Transformations.map(data) { it.Active }
    val deathCount: LiveData<Int> = Transformations.map(data) { it.Deaths }
    val recoveredCount: LiveData<Int> = Transformations.map(data) { it.Recovered }

    val onLaunchDetailsActivity : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    override fun onCreate() {

    }


    fun saveItemInDatabase(
        countryName: String?,
        provinceName: String?,
        confirmedCount: String?,
        deathCount:String?,
        recoveredCount: String?,
        activeCount: String?
    ) {
        compositeDisposable.addAll(
            Single.just(SavedItemEntity(null,countryName,provinceName,confirmedCount,deathCount,recoveredCount,activeCount))
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        searchedCountriesDataRepository.saveItemsInDB(it)
                    },
                    {

                    }
                )
        )
    }

    fun onLaunchDetailsView() {
        onLaunchDetailsActivity.postValue(Event(emptyMap()))
    }

}