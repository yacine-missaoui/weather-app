package com.example.weatherapp.core.domain.model

data class LocatedTown
(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String,
)
