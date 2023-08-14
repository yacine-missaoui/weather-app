package com.example.weatherapp.core.data.remote.mapper

import com.example.weatherapp.core.data.remote.dto.response.LocatedTownProperty
import com.example.weatherapp.core.domain.model.LocatedTown

fun LocatedTownProperty.toEntity(): LocatedTown{
    return LocatedTown(
        name= name,
        lat= lat,
        lon= lon,
        country= country,
        state= state
    )
}