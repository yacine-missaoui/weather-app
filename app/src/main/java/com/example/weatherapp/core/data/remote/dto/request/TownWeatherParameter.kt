package com.example.weatherapp.core.data.remote.dto.request

import com.example.weatherapp.core.common.NetworkConstants

data class TownWeatherParameter(
    val lat: Double,
    val lon: Double,
    val exclude: String ="hourly,daily",
    val appid: String = NetworkConstants.WEATHER_API_KEY
)
