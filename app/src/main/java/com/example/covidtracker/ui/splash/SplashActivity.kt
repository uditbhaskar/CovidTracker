package com.example.covidtracker.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.covidtracker.databinding.ActivitySplashBinding
import com.example.covidtracker.di.component.ActivityComponent
import com.example.covidtracker.ui.base.BaseActivity
import com.example.covidtracker.ui.home.HomeActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>() {

    @Inject
    lateinit var binding: ActivitySplashBinding

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutView(): View = binding.root

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.launchHome.observe(this, Observer {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
            }
        })
    }

}