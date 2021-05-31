package com.example.covidtracker.data.repository

import com.example.covidtracker.data.local.DatabaseService
import com.example.covidtracker.data.local.entity.SavedItemEntity
import com.example.covidtracker.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class SearchedCountryDataRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchAllAvailableCountries(countryName: String, yesterdayDate: String) =
        networkService.fetchDataOfCountry(countryName, yesterdayDate)

    fun saveItemsInDB(entity: SavedItemEntity) {
        databaseService.savedItemDao().insert(entity)
    }

    fun fetchItemsFromDB(): Single<List<SavedItemEntity>> =
        databaseService.savedItemDao().getAll()

}