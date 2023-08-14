package com.example.weatherapp.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "located_town_table")
data class LocatedTownTable (
    @PrimaryKey
    val id: Long = 1,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "lon")
    val lon: Double,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "state")
    val state: String
    )

