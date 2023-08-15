package com.example.weatherapp.core.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.weatherapp.core.domain.model.LocatedTown
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.ceil

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

    fun kelvinToCelsius(kelvin: Double): Int {
        return ceil(kelvin - 273.15).toInt()
    }
    fun getTimeFromTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = Date(timestamp * 1000)
        return sdf.format(date)
    }

    fun getUvIndexLevel(uvIndex: Double): String {
        return when (uvIndex) {
            in 0.0..2.9 -> "Low"
            in 3.0..5.9 -> "Moderate"
            in 6.0..7.9 -> "High"
            in 8.0..10.9 -> "Very High"
            else -> "Extreme"
        }
    }

    fun capitalizeFirstLetter(input: String): String {
        if (input.isEmpty()) return input
        return input.substring(0, 1).uppercase(Locale.ROOT) + input.substring(1)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}