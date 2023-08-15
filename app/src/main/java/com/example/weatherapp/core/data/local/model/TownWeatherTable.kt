package com.example.weatherapp.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "town_weather_table")
data class TownWeatherTable (
    @PrimaryKey
    val id: Long?,

    @ColumnInfo(name = "town_id")
    val townId: String,

    @ColumnInfo(name = "timeZone")
    val timeZone: String,

    @ColumnInfo(name = "sunrise")
    val sunrise: Long,

    @ColumnInfo(name = "sunset")
    val sunset: Long,

    @ColumnInfo(name = "main_weather")
    val mainWeather: String,

    @ColumnInfo(name = "weather_description")
    val weatherDescription: String,

    @ColumnInfo(name = "temp")
    val temp: Double,

    @ColumnInfo(name = "feels_like")
    val feelsLike: Double,

    @ColumnInfo(name = "uvi")
    val uvi: Double,

    @ColumnInfo(name = "wind")
    val wind: Int,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

)