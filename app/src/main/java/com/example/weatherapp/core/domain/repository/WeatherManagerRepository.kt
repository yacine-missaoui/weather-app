package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.domain.model.LocatedTown

interface WeatherManagerRepository {

    suspend fun geoLocateTown(name: String): DataResult<List<LocatedTown>>

    suspend fun addLocatedTown(locatedTown: LocatedTown): DataResult<Unit>
    suspend fun fetchLocatedTowns(): DataResult<List<LocatedTown>>

}