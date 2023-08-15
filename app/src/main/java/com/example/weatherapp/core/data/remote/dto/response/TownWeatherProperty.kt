package com.example.weatherapp.core.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TownWeatherProperty(
    val timezone: String,
    val current: CurrentWeatherProperty
)
