package com.example.covidtracker.data.repository

import com.example.covidtracker.data.local.DatabaseService
import com.example.covidtracker.data.model.Dummy
import com.example.covidtracker.data.remote.request.DummyRequest
import com.example.covidtracker.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchDummy(id: String): Single<List<Dummy>> =
        networkService.doDummyCall(DummyRequest(id)).map { it.data }

}