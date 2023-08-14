package com.example.weatherapp.core.data.repository

import android.net.http.HttpException
import android.util.Log
import com.example.weatherapp.core.common.ApiClient
import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.data.remote.dto.request.GeoLocateTownParameter
import com.example.weatherapp.core.data.remote.mapper.toEntity
import com.example.weatherapp.core.data.remote.mapper.toMap
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.core.domain.repository.WeatherManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherManagerRepositoryImpl : WeatherManagerRepository {
    override suspend fun geoLocateTown(name: String): DataResult<List<LocatedTown>> {
        return withContext(Dispatchers.IO){
            try {
                val resp = ApiClient.weatherApiService.geoLocateTown(GeoLocateTownParameter(q = name).toMap())

                DataResult.Success(resp.map {
                    it.toEntity()
                })
            }catch (ex: Exception){
                ex.printStackTrace()
                DataResult.Error(ex)
            }

        }
    }
}