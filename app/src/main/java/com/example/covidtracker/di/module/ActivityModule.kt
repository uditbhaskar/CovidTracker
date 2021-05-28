package com.example.covidtracker.di.module


import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityHomeBinding
import com.example.covidtracker.databinding.ActivitySplashBinding
import com.example.covidtracker.ui.base.BaseActivity
import com.example.covidtracker.ui.home.HomeViewModel
import com.example.covidtracker.ui.splash.SplashViewModel
import com.example.covidtracker.utils.ViewModelProviderFactory
import com.example.covidtracker.utils.network.NetworkHelper
import com.example.covidtracker.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


    @Provides
    fun providesSplashBinding() = ActivitySplashBinding.inflate(activity.layoutInflater)

    @Provides
    fun providesSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper
            )
        }).get(SplashViewModel::class.java)

    @Provides
    fun provideHomeBinding() = ActivityHomeBinding.inflate(activity.layoutInflater)

    @Provides
    fun providesHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): HomeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper
            )
        }).get(HomeViewModel::class.java)


}

