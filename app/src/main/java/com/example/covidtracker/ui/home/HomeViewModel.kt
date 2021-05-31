package com.example.covidtracker.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.covidtracker.data.local.entity.SavedItemEntity
import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import com.example.covidtracker.data.repository.SearchedCountryDataRepository
import com.example.covidtracker.ui.base.BaseViewModel
import com.example.covidtracker.utils.common.Resource
import com.example.covidtracker.utils.common.TimeUtils
import com.example.covidtracker.utils.network.NetworkHelper
import com.example.covidtracker.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val searchedCountriesDataRepository: SearchedCountryDataRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val searchQueryData: MutableLiveData<Resource<List<SearchedCountryDataResponse>>> = MutableLiveData()
    val savedHistoryData: MutableLiveData<Resource<List<SavedItemEntity>>> = MutableLiveData()

    override fun onCreate() {
        onFetchingHistoryData()
    }

    fun onFetchingHistoryData() {
        compositeDisposable.add(
            searchedCountriesDataRepository.fetchItemsFromDB()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        savedHistoryData.postValue(Resource.success(it))
                    },
                    {

                    }
                )
        )

    }


    fun onSearching(q: String) {
        if (checkInternetConnectionWithMessage()) {
            if (q.isNotEmpty()) {
                compositeDisposable.addAll(
                    searchedCountriesDataRepository.fetchAllAvailableCountries(q,TimeUtils.getYesterdayDate())
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                                searchQueryData.postValue(Resource.success(it))
                            }, {
                                handleNetworkError(it)
                            }
                        )
                )
            } else {
                messageString.postValue(Resource.error("Empty search field."))
            }
        }
    }

}