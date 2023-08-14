package com.example.weatherapp.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.core.data.local.dao.WeatherDao
import com.example.weatherapp.core.data.local.model.LocatedTownTable

@Database(
    entities = [
        LocatedTownTable::class
        ],
    version = 1,
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao

    companion object {
        private const val DATABASE_NAME = "weather_database"

        @Volatile
        private var INSTANCE: WeatherDatabase? = null
        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
