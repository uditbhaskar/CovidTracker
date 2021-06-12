package com.example.covidtracker.data.repository


import com.example.covidtracker.data.mongodb.DatabaseService
import com.example.covidtracker.data.mongodb.realm_object_model.CovidData
import com.example.covidtracker.data.remote.NetworkService

import javax.inject.Inject


class SearchedCountryDataRepository @Inject constructor(

    private val networkService: NetworkService,
    private val databaseService: DatabaseService

) {

    fun fetchAllAvailableCountries(countryName: String, yesterdayDate: String) =
        networkService.fetchDataOfCountry(countryName, yesterdayDate)

    fun saveItemsInDB(
        countryName: String?,
        provinceName: String?,
        confirmedCount: String?,
        deathCount: String?,
        recoveredCount: String?,
        activeCount: String?
    ) {
        databaseService.saveToDatabase(
            countryName,
            provinceName,
            confirmedCount,
            deathCount,
            recoveredCount,
            activeCount
        )
    }

    fun fetchItemsFromDB(): List<CovidData>? {

        return databaseService.fetchFromMongo()?.reversed()
    }


}