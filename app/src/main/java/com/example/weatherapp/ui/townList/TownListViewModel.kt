package com.example.weatherapp.ui.townList

import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.domain.model.Town
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TownListViewModel (): ViewModel() {

    private val _townsState = MutableStateFlow<List<Town>>(emptyList())
    val townsState: StateFlow<List<Town>> = _townsState

}