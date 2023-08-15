package com.example.weatherapp.core.data.remote.mapper

import com.example.weatherapp.core.data.local.model.TownWeatherTable
import com.example.weatherapp.core.data.remote.dto.response.TownWeatherProperty
import com.example.weatherapp.core.domain.model.TownWeather

fun TownWeatherProperty.asDomainModel(): TownWeather{
    return TownWeather(
        timeZone = timezone,
        sunrise = current.sunrise,
        sunset = current.sunset,
        mainWeather = current.weather.main,
        weatherDescription = current.weather.description,
        temp = current.temp,
        feelsLike = current.feels_like,
        uvi = current.uvi,
        wind = current.wind_speed,
        humidity = current.humidity,
    )
}

fun TownWeatherProperty.asDatabaseModel(townId: String): TownWeatherTable{
    return TownWeatherTable(
        id = null,
        townId = townId,
        timeZone = timezone,
        sunrise = current.sunrise,
        sunset = current.sunset,
        mainWeather = current.weather.main,
        weatherDescription = current.weather.description,
        temp = current.temp,
        feelsLike = current.feels_like,
        uvi = current.uvi,
        wind = current.wind_speed,
        humidity = current.humidity,
    )
}