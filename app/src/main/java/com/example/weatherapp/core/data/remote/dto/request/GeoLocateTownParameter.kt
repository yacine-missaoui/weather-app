package com.example.weatherapp.core.data.remote.dto.request

import com.example.weatherapp.core.common.NetworkConstants

data class GeoLocateTownParameter(
    val q: String,
    val limit: Int = 1,
    val appid: String = NetworkConstants.WEATHER_API_KEY
)
