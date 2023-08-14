package com.example.weatherapp.ui.townAdd

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.data.repository.WeatherManagerRepositoryImpl
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.core.domain.repository.WeatherManagerRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TownAddViewModel (): ViewModel() {

    private val weatherManagerRepository : WeatherManagerRepository = WeatherManagerRepositoryImpl()

    private val searchChannel = Channel<List<LocatedTown>>()
    val searchFlow = searchChannel.receiveAsFlow()

    fun searchForTown(townName: String)
    {
        Log.d("weather_app", "searchForTown: $townName")
        viewModelScope.launch {
            when(val result = weatherManagerRepository.geoLocateTown(townName)){
                is DataResult.Success -> {
                    Log.d("weather_app", "Success: ${result.data}")
                    searchChannel.send(result.data)
                }
                is DataResult.Error -> {
                    Log.d("weather_app", "Error: ${result.exception.message}")
                    searchChannel.send(emptyList())
                }
            }
        }
    }
}