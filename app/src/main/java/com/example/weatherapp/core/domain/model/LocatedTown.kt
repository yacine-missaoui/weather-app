package com.example.weatherapp.core.domain.model

data class LocatedTown
(
    val id: String?,
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String,
    val createdAt: String?
)
