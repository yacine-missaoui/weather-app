package com.example.weatherapp.ui.townDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.core.common.Utils
import com.example.weatherapp.core.domain.model.TownWeather
import com.example.weatherapp.databinding.ActivityTownDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TownDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTownDetailsBinding
    private val viewModel: TownDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTownDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        observeWeather()

        val townId = intent.getStringExtra("id")
        val townLat = intent.getDoubleExtra("lat", 0.0)
        val townLon = intent.getDoubleExtra("lon", 0.0)

        townId?.let {
            if(Utils.isInternetAvailable(this))
            {
                viewModel.fetchTownData(it, townLat, townLon)
            }else {
                viewModel.fetchTownDataFromCache(it)
            }
        }
    }
    private fun observeWeather()
    {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.weatherFlow.collect { res ->
                showContent()
                displayData(res)
            }
        }
    }

    private fun showContent() {
        with(binding)
        {
            weatherProgressBar.visibility = View.GONE
            weatherContent.visibility = View.VISIBLE
        }
    }

    private fun displayData(townWeather: TownWeather){
        with(binding)
        {
            val tempTxt = "${Utils.kelvinToCelsius(townWeather.temp)}°"
            tempTextView.text= tempTxt
            timezoneTextView.text= townWeather.timeZone
            val flTxt = "Feels like ${Utils.kelvinToCelsius(townWeather.feelsLike)}°"
            feelsLikeTextView.text= flTxt
            weatherDescriptionTextview.text= Utils.capitalizeFirstLetter(townWeather.weatherDescription)
            sunriseTextView.text= Utils.getTimeFromTimestamp(townWeather.sunrise)
            sunsetTextView.text= Utils.getTimeFromTimestamp(townWeather.sunset)
            mainWeatherTextView.text= townWeather.mainWeather
            val humTxt = "${townWeather.humidity}%"
            humidityTextView.text= humTxt
            uviTextView.text= Utils.getUvIndexLevel(townWeather.uvi)
            val windTxt = "${townWeather.wind} km/h"
            windTextView.text= windTxt
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}