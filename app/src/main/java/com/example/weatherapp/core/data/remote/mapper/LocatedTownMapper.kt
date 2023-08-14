package com.example.weatherapp.core.data.remote.mapper

import com.example.weatherapp.core.data.local.model.LocatedTownTable
import com.example.weatherapp.core.data.remote.dto.response.LocatedTownProperty
import com.example.weatherapp.core.domain.model.LocatedTown

fun LocatedTownProperty.asDomainModel(): LocatedTown{
    return LocatedTown(
        id= null,
        name= name,
        lat= lat,
        lon= lon,
        country= country,
        state= state
    )
}

fun LocatedTown.asDatabaseModel(): LocatedTownTable{
    return LocatedTownTable(
        id = name+country,
        name= name,
        lat= lat,
        lon= lon,
        country= country,
        state= state
    )
}