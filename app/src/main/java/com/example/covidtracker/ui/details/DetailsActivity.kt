package com.example.covidtracker.ui.details

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.covidtracker.databinding.ActivityDetailsBinding
import com.example.covidtracker.di.component.ActivityComponent
import com.example.covidtracker.ui.base.BaseActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import java.util.*
import javax.inject.Inject

class DetailsActivity : BaseActivity<DetailsViewModel>() {


    companion object {
        const val TAG = "DetailsActivity"
    }

    @Inject
    lateinit var binding: ActivityDetailsBinding

    override fun provideLayoutView(): View = binding.root

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun setupView(savedInstanceState: Bundle?) {

        binding.ivBack.setOnClickListener {
            viewModel.onBackPressed()
        }

        binding.tvCountryDetails.text = "Country :  "+intent.getStringExtra("countryName")
        binding.tvProvinceDetails.text = "Province :  "+intent.getStringExtra("provinceName")
        binding.tvTotalCasesDetails.text = "Total Cases :  "+intent.getStringExtra("countConfirmed")


        binding.chart.apply {

            description.apply {
                text = "Types of cases"
                textSize = 10F
                textColor = Color.GRAY
                isRotationEnabled = true
                holeRadius = 10F
                setTransparentCircleAlpha(0)
            }

            val countOfEachCategories = arrayOf(
                intent.getStringExtra("countDeaths").toFloat(),
                intent.getStringExtra("countRecovered").toFloat(),
                intent.getStringExtra("countActive").toFloat()
            )
            val categories = arrayOf("Deaths.", "Recoveries.", "Active.")

            val yEntry = ArrayList<PieEntry>()

            for (i in categories.indices) {
                yEntry.add(PieEntry(countOfEachCategories[i], categories[i]))

            }

            val dataSet = PieDataSet(yEntry, "").apply {
                valueFormatter = DefaultValueFormatter(0)
                sliceSpace = 3f
                setDrawValues(false)
            }

            val colors = ArrayList<Int>().apply {
                add(Color.BLUE)
                add(Color.BLACK)
                add(Color.RED)
            }

            dataSet.colors = colors

            binding.chart.legend.apply {
                isEnabled = true
                form = Legend.LegendForm.CIRCLE
                direction = Legend.LegendDirection.LEFT_TO_RIGHT
            }


            val pieData = PieData(dataSet)

            binding.chart.data = pieData
            binding.chart.invalidate()
            binding.chart.setDrawEntryLabels(false)
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchHome.observe(this, Observer {
            it.getIfNotHandled()?.run {
                finish()
            }
        })
    }
}