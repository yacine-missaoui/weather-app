package com.example.weatherapp.ui.townList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.common.DataResult
import com.example.weatherapp.core.data.repository.WeatherManagerRepositoryImpl
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.core.domain.repository.WeatherManagerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TownListViewModel : ViewModel() {

    private val _townsState = MutableStateFlow<List<LocatedTown>>(emptyList())
    val townsState: StateFlow<List<LocatedTown>> = _townsState
    private val weatherManagerRepository : WeatherManagerRepository = WeatherManagerRepositoryImpl()

    fun fetchTowns()
    {
        viewModelScope.launch {

            when(val result = weatherManagerRepository.fetchLocatedTowns())
            {
                is DataResult.Success -> {
                    _townsState.emit(result.data)
                }
                is DataResult.Error -> _townsState.emit(emptyList())
            }
        }
    }

}