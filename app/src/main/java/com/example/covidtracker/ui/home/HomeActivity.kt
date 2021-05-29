package com.example.covidtracker.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.covidtracker.databinding.ActivityHomeBinding
import com.example.covidtracker.di.component.ActivityComponent
import com.example.covidtracker.ui.base.BaseActivity
import com.example.covidtracker.utils.common.DismissKeyboard
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

        binding.etSearch.setOnClickListener {

            viewModel.onSearching(binding.searchBar.text.toString())

            DismissKeyboard.hideSoftKeyBoard(
                this,
                window.decorView.findViewById(android.R.id.content)
            )
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.searchQueryData.observe(this, Observer {
            it.data.run {
                if (this.isNullOrEmpty()) {
                    binding.rvSearchResult.visibility = View.GONE
                    binding.tvNoSearch.visibility = View.VISIBLE
                } else {
                    binding.rvSearchResult.visibility = View.VISIBLE
                    binding.tvNoSearch.visibility = View.VISIBLE
                }
             //   searchListAdapter.appendData(this)
             //   searchListAdapter.notifyDataSetChanged()
            }
        })

    }
}