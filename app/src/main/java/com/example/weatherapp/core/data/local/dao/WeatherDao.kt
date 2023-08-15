package com.example.weatherapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.core.data.local.model.LocatedTownTable
import com.example.weatherapp.core.data.local.model.TownWeatherTable

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLocatedTown(locatedTown: LocatedTownTable)

    @Query("SELECT * FROM located_town_table")
    suspend fun fetchLocatedTowns(): List<LocatedTownTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTownWeather(townWeather: TownWeatherTable)

    @Query("SELECT * FROM town_weather_table WHERE :townId = town_id LIMIT 1")
    suspend fun fetchTownWeatherByTownId(townId:String): TownWeatherTable

}