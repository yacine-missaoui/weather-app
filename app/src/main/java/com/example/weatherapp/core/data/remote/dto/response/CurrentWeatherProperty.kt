package com.example.weatherapp.core.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherProperty(
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val wind_speed: Int,
    val uvi: Double,
    val weather: WeatherProperty
)
