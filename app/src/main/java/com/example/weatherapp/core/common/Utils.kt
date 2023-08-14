package com.example.weatherapp.core.common

import com.example.weatherapp.core.domain.model.LocatedTown
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    const val TAG = "weather_app"
    
    fun getCountryNameFromCode(countryCode: String): String {
        val locale = Locale("", countryCode)
        return locale.displayCountry
    }

    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("EEE, dd MMMM, HH:mm", Locale.getDefault())
        return dateFormat.format(Date())
    }


    fun generateTownDisplayName(town: LocatedTown): String
    {
        return "${town.name}, ${town.state}, ${getCountryNameFromCode(town.country)}"
    }
}