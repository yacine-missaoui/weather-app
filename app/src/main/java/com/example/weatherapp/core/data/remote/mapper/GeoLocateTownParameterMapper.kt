package com.example.weatherapp.core.data.remote.mapper

import com.example.weatherapp.core.data.remote.dto.request.GeoLocateTownParameter

fun GeoLocateTownParameter.toMap(): Map<String, String>{
    return mapOf(
        "q" to q,
        "limit" to limit.toString(),
        "appid" to appid
    )
}