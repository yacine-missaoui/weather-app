package com.example.weatherapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.core.data.local.model.LocatedTownTable


@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocatedTown(locatedTown: LocatedTownTable)

    @Query("SELECT * FROM located_town_table")
    suspend fun fetchLocatedTowns(): List<LocatedTownTable>
}