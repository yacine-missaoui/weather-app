package com.example.weatherapp.core.common

import android.content.Context
import com.example.weatherapp.core.data.local.dao.WeatherDao
import com.example.weatherapp.core.data.local.database.WeatherDatabase

object DatabaseModule {
    lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun townDao(): WeatherDao
    {
         return WeatherDatabase.getDatabase(appContext).weatherDao

    }

}