package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.domain.model.LocatedTown

interface WeatherManagerRepository {

    suspend fun geoLocateTown(name: String): DataResult<List<LocatedTown>>

}