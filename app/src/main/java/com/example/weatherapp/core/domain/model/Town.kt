package com.example.weatherapp.core.domain.model

data class Town(val timeZone:String, val sunrise: Long, val sunset: Long, val weather: String,
                val tempMin: Double, val tempMax: Double)