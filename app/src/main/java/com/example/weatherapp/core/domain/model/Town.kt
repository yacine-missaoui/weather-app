package com.example.weatherapp.core.domain.model

data class Town(
    val timeZone:String,
    val sunrise: Long,
    val sunset: Long,
    val mainWeather: String,
    val weatherDescription: String,
    val temp: Double,
    val feelsLike: Double,
    val uvi: Double,
    val wind: Int,
    val humidity: Int,

    )