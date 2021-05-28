package com.example.covidtracker.ui.home

import android.os.Bundle
import android.view.View
import com.example.covidtracker.databinding.ActivityHomeBinding
import com.example.covidtracker.di.component.ActivityComponent
import com.example.covidtracker.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel>() {

    @Inject
    lateinit var binding: ActivityHomeBinding

    companion object {
        const val TAG = "HomeActivity"
    }


    override fun provideLayoutView(): View = binding.root

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel
    }
}