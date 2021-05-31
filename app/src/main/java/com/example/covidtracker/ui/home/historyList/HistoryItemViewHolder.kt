package com.example.covidtracker.ui.home.historyList

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.covidtracker.R
import com.example.covidtracker.data.local.entity.SavedItemEntity
import com.example.covidtracker.di.component.ViewHolderComponent
import com.example.covidtracker.ui.base.BaseItemViewHolder

class HistoryItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<SavedItemEntity, HistoryItemViewModel>(R.layout.saved_item, parent) {

    private var countryName: String? = null
    private var provinceName: String? = null
    private var countConfirmed: String? = null
    private var countDeaths: String? = null
    private var countRecovered: String? = null
    private var countActive: String? = null

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        itemView.setOnClickListener {
            viewModel.onLaunchDetailsActivity()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {
        super.setupObservers()


        viewModel.countryName.observe(this, Observer {
            it.run {
                itemView.findViewById<TextView>(R.id.tv_country_history).text = it

            }
            countryName = it

        })

        viewModel.provinceName.observe(this, Observer {
            it?.run {
                if (this.isNotBlank() && this.isNotEmpty()) {
                    itemView.findViewById<TextView>(R.id.tv_province_history).run {
                        visibility = View.VISIBLE
                        text = "Province: $it"
                        provinceName = it

                    }
                }else{
                    itemView.findViewById<TextView>(R.id.tv_province_history).run {
                        text = "Province: none"
                        provinceName = ""
                    }
                }
            }
        })

        viewModel.confirmedCount.observe(this, Observer {
            it.run {
                itemView.findViewById<TextView>(R.id.tv_total_cases_history).run {
                    text = "Confirmed Cases: $it"
                }

                countConfirmed = it
            }
        })

        viewModel.activeCount.observe(this, Observer {
            countActive = it
        })

        viewModel.deathCount.observe(this, Observer {
            countDeaths = it
        })

        viewModel.recoveredCount.observe(this, Observer {
            countRecovered = it
        })


    }
}