package com.example.weatherapp.core.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class LocatedTownProperty(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String,
)
