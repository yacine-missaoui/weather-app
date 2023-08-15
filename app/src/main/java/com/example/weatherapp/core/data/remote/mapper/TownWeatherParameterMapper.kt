package com.example.weatherapp.core.data.remote.mapper

import com.example.weatherapp.core.data.remote.dto.request.TownWeatherParameter

fun TownWeatherParameter.toMap(): Map<String, String> {
    return mapOf(
        "lat" to lat.toString(),
        "lon" to lon.toString(),
        "exclude" to exclude,
        "appid" to appid
    )
}