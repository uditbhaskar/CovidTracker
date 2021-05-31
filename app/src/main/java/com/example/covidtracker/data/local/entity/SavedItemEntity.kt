package com.example.covidtracker.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "savedItem_entity")
data class SavedItemEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long?,

    @ColumnInfo(name = "Country")
    @NotNull
    val Country: String?,

    @ColumnInfo(name = "Province")
    @NotNull
    val Province: String?,

    @ColumnInfo(name = "Confirmed")
    @NotNull
    val Confirmed: String?,

    @ColumnInfo(name = "Deaths")
    @NotNull
    val Deaths: String?,

    @ColumnInfo(name = "Recovered")
    @NotNull
    val Recovered: String?,

    @ColumnInfo(name = "Active")
    @NotNull
    val Active: String?
)