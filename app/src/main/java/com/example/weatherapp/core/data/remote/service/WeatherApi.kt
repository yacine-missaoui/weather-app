package com.example.weatherapp.core.data.remote.service
import com.example.weatherapp.core.common.NetworkConstants.GEO_ENDPOINT
import com.example.weatherapp.core.common.NetworkConstants.WEATHER_ENDPOINT
import com.example.weatherapp.core.data.remote.dto.response.LocatedTownProperty
import com.example.weatherapp.core.data.remote.dto.response.TownWeatherProperty
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherApi {

    @GET(GEO_ENDPOINT)
    suspend fun geoLocateTown(@QueryMap parameter: Map<String, String>): List<LocatedTownProperty>

    @GET(WEATHER_ENDPOINT)
    suspend fun getTownWeather(@QueryMap parameter: Map<String, String>): TownWeatherProperty


}