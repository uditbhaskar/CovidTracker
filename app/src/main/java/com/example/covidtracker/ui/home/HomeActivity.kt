package com.example.covidtracker.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityHomeBinding
import com.example.covidtracker.di.component.ActivityComponent
import com.example.covidtracker.ui.base.BaseActivity
import com.example.covidtracker.ui.home.historyList.HistoryAdapter
import com.example.covidtracker.ui.home.searchList.SearchAdapter
import com.example.covidtracker.utils.common.DismissKeyboard
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel>() {

    @Inject
    lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var searchListAdapter: SearchAdapter

    @Inject
    lateinit var historyAdapter: HistoryAdapter

    companion object {
        const val TAG = "HomeActivity"
    }


    override fun provideLayoutView(): View = binding.root

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {




        binding.etSearch.setOnClickListener {

            viewModel.onSearching(binding.searchBar.text.trim().toString())

            DismissKeyboard.hideSoftKeyBoard(
                this,
                window.decorView.findViewById(android.R.id.content)
            )
        }

        binding.rvSearchResult.apply {
            layoutManager = linearLayoutManager
            adapter = searchListAdapter
        }

        binding.rvHistory.apply {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = historyAdapter
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
                this?.let { it1 -> searchListAdapter.appendData(it1) }
                searchListAdapter.notifyDataSetChanged()
            }
        })


        viewModel.savedHistoryData.observe(this, Observer {
            it.data?.run {

                if(this.isNullOrEmpty()){
                    binding.rvHistory.visibility=View.GONE
                    binding.noHistoryLayout.visibility=View.VISIBLE
                }else{
                    binding.rvHistory.visibility=View.VISIBLE
                    binding.noHistoryLayout.visibility=View.GONE
                }
                historyAdapter.appendData(this)
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onFetchingHistoryData()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onFetchingHistoryData()
    }
}