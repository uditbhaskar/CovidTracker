package com.example.covidtracker.data.mongodb.realm_object_model


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId


open class CovidData(

    _Country: String? = null,
    _Province: String? = null,
    _Confirmed: String? = null,
    _Deaths: String? = null,
    _Recovered: String? = null,
    _Active: String? = null

) : RealmObject() {

    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var Country: String? = _Country

    var Province: String? = _Province

    var Confirmed: String? = _Confirmed

    var Deaths: String? = _Deaths

    var Recovered: String? = _Recovered

    var Active: String? = _Active
}