package com.example.weatherapp.core.common

import com.example.weatherapp.core.common.NetworkConstants.BASE_URL
import com.example.weatherapp.core.data.remote.service.WeatherApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val okHttpClient = OkHttpClient().newBuilder()
    .build()

    val weatherApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)
}