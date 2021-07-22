package com.example.testweather.di

import com.example.testweather.data.network.LocationApi
import com.example.testweather.data.network.WeatherApi
import com.example.testweather.utils.OPEN_CAGE_URL
import com.example.testweather.utils.OPEN_WEATHER_MAP_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(OPEN_CAGE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_MAP_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}