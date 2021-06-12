package com.example.covidtracker.data.mongodb

import com.example.covidtracker.covidApp
import com.example.covidtracker.data.mongodb.realm_object_model.CovidData
import com.example.covidtracker.ui.home.searchList.SearchItemViewModel
import com.example.covidtracker.utils.log.Logger
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration


class DatabaseService {

    fun saveToDatabase(
        countryName: String?,
        provinceName: String?,
        confirmedCount: String?,
        deathCount: String?,
        recoveredCount: String?,
        activeCount: String?
    ) {
        try {
            val credentials: Credentials = Credentials.anonymous()
            covidApp.loginAsync(credentials) {
                if (it.isSuccess) {


                    val user: User? = covidApp.currentUser()
                    val partitionValue = "My Project"

                    val config = SyncConfiguration.Builder(user, partitionValue)
                        .allowQueriesOnUiThread(true)
                        .allowWritesOnUiThread(true)
                        .build()

                    val backgroundThreadRealm = Realm.getInstance(config)

                    val covidData = CovidData(
                        countryName,
                        provinceName,
                        confirmedCount,
                        deathCount,
                        recoveredCount,
                        activeCount
                    )

                    backgroundThreadRealm.executeTransaction { transactionRealm ->
                        transactionRealm.insert(covidData)
                    }


                } else {
                    Logger.e(SearchItemViewModel.TAG, "Failed. Error:- ${it.error}")
                }
            }

        } catch (e: Exception) {
            Logger.d(SearchItemViewModel.TAG, e.toString())
        }
    }

    fun fetchFromMongo(): List<CovidData>? {
        val credentials: Credentials = Credentials.anonymous()
        covidApp.loginAsync(credentials){

        }

        val user: User? = covidApp.currentUser()
        val partitionValue = "My Project"

        val config = SyncConfiguration.Builder(user, partitionValue)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()

        val backgroundThreadRealm = Realm.getInstance(config)

        return backgroundThreadRealm.where<CovidData>().findAll()

    }
}