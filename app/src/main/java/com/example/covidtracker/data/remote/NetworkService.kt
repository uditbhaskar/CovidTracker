package com.example.covidtracker.data.remote

import com.example.covidtracker.data.remote.request.DummyRequest
import com.example.covidtracker.data.remote.response.DummyResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @POST(Endpoints.DUMMY)
    fun doDummyCall(
        @Body request: DummyRequest
    ): Single<DummyResponse>
}