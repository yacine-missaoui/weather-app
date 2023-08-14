package com.example.weatherapp.core.data.local.mapper

import com.example.weatherapp.core.data.local.model.LocatedTownTable
import com.example.weatherapp.core.domain.model.LocatedTown

fun LocatedTownTable.asDomainModel():LocatedTown{
    return LocatedTown(
        id, name, lat, lon, country, state
    )
}