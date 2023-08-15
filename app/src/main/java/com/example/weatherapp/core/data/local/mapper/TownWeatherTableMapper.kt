package com.example.weatherapp.core.data.local.mapper

import com.example.weatherapp.core.data.local.model.TownWeatherTable
import com.example.weatherapp.core.domain.model.TownWeather

fun TownWeatherTable.asDomainModel(): TownWeather{
    return TownWeather(
        timeZone = timeZone,
        sunrise= sunrise,
        sunset= sunset,
        temp = temp,
        feelsLike = feelsLike,
        humidity = humidity,
        uvi = uvi,
        wind = wind,
        mainWeather = mainWeather,
        weatherDescription = weatherDescription
    )
}