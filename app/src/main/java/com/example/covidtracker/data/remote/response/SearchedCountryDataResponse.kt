package com.example.covidtracker.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchedCountryDataResponse(
    @Expose
    @SerializedName("Country")
    var Country: String,
    @Expose
    @SerializedName("Province")
    var Province: String,
    @Expose
    @SerializedName("Confirmed")
    var Confirmed: Int,
    @Expose
    @SerializedName("Deaths")
    var Deaths:Int,
    @Expose
    @SerializedName("Recovered")
    var Recovered:Int,
    @Expose
    @SerializedName("Active")
    var Active:Int


)
