package com.example.weatherapp.core.data.repository

import android.util.Log
import com.example.weatherapp.core.common.ApiClient
import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.common.DatabaseModule
import com.example.weatherapp.core.data.local.mapper.asDomainModel
import com.example.weatherapp.core.data.remote.dto.request.GeoLocateTownParameter
import com.example.weatherapp.core.data.remote.mapper.asDatabaseModel
import com.example.weatherapp.core.data.remote.mapper.asDomainModel
import com.example.weatherapp.core.data.remote.mapper.toMap
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.core.domain.repository.WeatherManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherManagerRepositoryImpl : WeatherManagerRepository {

    private val weatherDao = DatabaseModule.townDao()
    override suspend fun geoLocateTown(name: String): DataResult<List<LocatedTown>> {
        return withContext(Dispatchers.IO){
            try {
                val resp = ApiClient.weatherApiService.geoLocateTown(GeoLocateTownParameter(q = name).toMap())

                DataResult.Success(resp.map {
                    it.asDomainModel()
                })
            }catch (ex: Exception){
                ex.printStackTrace()
                DataResult.Error(ex)
            }

        }
    }

    override suspend fun addLocatedTown(locatedTown: LocatedTown): DataResult<Unit> {
        return try {
            weatherDao.addLocatedTown(locatedTown.asDatabaseModel())
            DataResult.Success(Unit)
        }catch (ex: java.lang.Exception)
        {
            Log.d("weather_app", "Error adding located town")
            ex.printStackTrace()
            DataResult.Error(ex)
        }
    }

    override suspend fun fetchLocatedTowns(): DataResult<List<LocatedTown>> {
        return try {
            val res = weatherDao.fetchLocatedTowns().map {
                it.asDomainModel()
            }
            DataResult.Success(res)
        }catch (ex: Exception)
        {
            Log.d("weather_app", "Error fetching located town")
            ex.printStackTrace()
            DataResult.Error(ex)
        }
    }
}