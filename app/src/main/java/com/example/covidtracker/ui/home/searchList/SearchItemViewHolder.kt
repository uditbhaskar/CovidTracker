package com.example.covidtracker.ui.home.searchList

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.covidtracker.R
import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import com.example.covidtracker.di.component.ViewHolderComponent
import com.example.covidtracker.ui.base.BaseItemViewHolder
import com.example.covidtracker.ui.details.DetailsActivity

class SearchItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<SearchedCountryDataResponse, SearchItemViewModel>(
        R.layout.search_item_view,
        parent
    ) {

    private var countryName: String? = null
    private var provinceName: String? = "none"
    private var countConfirmed: String? = null
    private var countDeaths: String? = null
    private var countRecovered: String? = null
    private var countActive: String? = null

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        itemView.setOnClickListener {
            viewModel.saveItemInDatabase(
                countryName,
                provinceName,
                countConfirmed,
                countDeaths,
                countRecovered,
                countActive
            )
            viewModel.onLaunchDetailsView()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {
        super.setupObservers()


        viewModel.onLaunchDetailsActivity.observe(this, Observer {
            it.getIfNotHandled()?.run {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        DetailsActivity::class.java
                    )
                        .putExtra("countryName", countryName)
                        .putExtra("provinceName", provinceName)
                        .putExtra("countConfirmed", countConfirmed)
                        .putExtra("countDeaths", countDeaths)
                        .putExtra("countRecovered", countRecovered)
                        .putExtra("countActive", countActive)
                )
            }
        })

        viewModel.countryName.observe(this, Observer {
            it.run {
                itemView.findViewById<TextView>(R.id.tv_country).text = it
                countryName = it

            }
        })

        viewModel.provinceName.observe(this, Observer {
            it?.run {
                if (this.isNotBlank() && this.isNotEmpty()) {
                    itemView.findViewById<TextView>(R.id.tv_province).run {
                        visibility = View.VISIBLE
                        text = "Province: $it"
                        provinceName = it

                    }
                } else {
                    itemView.findViewById<TextView>(R.id.tv_province).run {
                        visibility = View.GONE
                        provinceName = "none"
                    }
                }
            }
        })

        viewModel.confirmedCount.observe(this, Observer {
            it.run {
                itemView.findViewById<TextView>(R.id.tv_confirmed).run {
                    text = "Confirmed Cases: $it"
                }

                countConfirmed = it.toString()
            }
        })

        viewModel.activeCount.observe(this, Observer {
            countActive = it.toString()
        })

        viewModel.deathCount.observe(this, Observer {
            countDeaths = it.toString()
        })

        viewModel.recoveredCount.observe(this, Observer {
            countRecovered = it.toString()
        })
    }

}