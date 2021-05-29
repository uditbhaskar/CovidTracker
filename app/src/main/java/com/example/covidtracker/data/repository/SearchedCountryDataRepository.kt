package com.example.covidtracker.data.repository

import com.example.covidtracker.data.local.DatabaseService
import com.example.covidtracker.data.remote.NetworkService
import javax.inject.Inject

class SearchedCountryDataRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchAllAvailableCountries(countryName: String, yesterdayDate: String) =
        networkService.fetchDataOfCountry(countryName, yesterdayDate)

}