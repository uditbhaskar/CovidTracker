package com.example.covidtracker.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.covidtracker.ui.base.BaseViewModel
import com.example.covidtracker.utils.common.Event
import com.example.covidtracker.utils.network.NetworkHelper
import com.example.covidtracker.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val launchHome = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
        launchHome.postValue(Event(true))
    }

}