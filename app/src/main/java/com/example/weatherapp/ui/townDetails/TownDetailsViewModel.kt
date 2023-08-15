package com.example.weatherapp.ui.townDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.common.Utils
import com.example.weatherapp.core.data.repository.WeatherManagerRepositoryImpl
import com.example.weatherapp.core.domain.model.TownWeather
import com.example.weatherapp.core.domain.repository.WeatherManagerRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TownDetailsViewModel(): ViewModel() {
    private val weatherManagerRepository : WeatherManagerRepository = WeatherManagerRepositoryImpl()
    private val weatherChannel = Channel<TownWeather>()
    val weatherFlow = weatherChannel.receiveAsFlow()

    fun fetchTownData(id:String, lat: Double, lon: Double )
    {
        viewModelScope.launch {

            when(val result = weatherManagerRepository.getTownWeather(id, lat, lon))
            {
                is DataResult.Success -> {
                    Log.d(Utils.TAG, "getTownWeather: ${result.data}")
                    weatherChannel.send(result.data)
                }
                is DataResult.Error -> {
                    Log.d(Utils.TAG, "getTownWeather: error ")
                    weatherChannel.send(
                        TownWeather(
                            timeZone = "America/Chicago",
                            sunrise = 1618282134,
                            sunset = 1618333901,
                            temp = 284.07,
                            feelsLike = 282.84,
                            humidity = 62,
                            uvi = 0.89,
                            wind = 6,
                            mainWeather = "Rain",
                            weatherDescription = "light rain",
                        )
                    )

                }
            }
        }

    }

    fun fetchTownDataFromCache(townId: String)
    {
        viewModelScope.launch{
            when(val result = weatherManagerRepository.fetchTownWeatherByTownId(townId))
            {
                is DataResult.Success -> {
                    Log.d(Utils.TAG, "checkAvailability: ${result.data}")
                    weatherChannel.send(result.data)
                }
                is DataResult.Error -> {
                    Log.d(Utils.TAG, "checkAvailability: error")
                }
            }
        }

    }
}