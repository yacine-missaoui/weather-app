package com.example.weatherapp.core.data.repository

import com.example.weatherapp.core.common.ApiClient
import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.common.DatabaseModule
import com.example.weatherapp.core.data.local.mapper.asDomainModel
import com.example.weatherapp.core.data.local.model.TownWeatherTable
import com.example.weatherapp.core.data.remote.dto.request.GeoLocateTownParameter
import com.example.weatherapp.core.data.remote.dto.request.TownWeatherParameter
import com.example.weatherapp.core.data.remote.mapper.asDatabaseModel
import com.example.weatherapp.core.data.remote.mapper.asDomainModel
import com.example.weatherapp.core.data.remote.mapper.toMap
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.core.domain.model.TownWeather
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
            ex.printStackTrace()
            DataResult.Error(ex)
        }
    }

    override suspend fun getTownWeather(id:String, lat: Double, lon: Double):
            DataResult<TownWeather> {
        return withContext(Dispatchers.IO){
            try {
                val resp = ApiClient.weatherApiService.getTownWeather(TownWeatherParameter(lat = lat, lon = lon).toMap())
                weatherDao.addTownWeather(resp.asDatabaseModel(id))
                DataResult.Success(resp.asDomainModel())
            }catch (ex: Exception){
                ex.printStackTrace()
                weatherDao.addTownWeather(
                    TownWeatherTable(
                        id= null,
                        townId = id,
                        timeZone = "America/Chicago",
                        sunrise = 1618282134,
                        sunset = 1618333901,
                        temp = 284.07,
                        feelsLike = 282.84,
                        humidity = 62,
                        uvi = 0.89,
                        wind = 6,
                        mainWeather = "Rain",
                        weatherDescription = "light rain"
                    )
                )
                DataResult.Error(ex)
            }
        }
    }

    override suspend fun fetchTownWeatherByTownId(id: String): DataResult<TownWeather> {
        return try {
            val res = weatherDao.fetchTownWeatherByTownId(id)

            DataResult.Success(res.asDomainModel())
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            DataResult.Error(ex)
        }
    }
}