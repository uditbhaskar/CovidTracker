package com.example.covidtracker.data.remote

import com.example.covidtracker.data.remote.response.SearchedCountryDataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.COUNTRY_DATA)
    fun fetchDataOfCountry(
        @Path(value = "COUNTRY_NAME", encoded = true) countryName: String,
        @Path(value = "DATE_YESTERDAY", encoded = true) yesterdayDate: String
    ): Single<List<SearchedCountryDataResponse>>       //fetching all countries with stats available by this api
}