package com.example.weatherapp.core.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class WeatherProperty(
    val main: String,
    val description: String,
)
