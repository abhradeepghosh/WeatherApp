package com.example.testweather.data.weather

import com.example.testweather.base.NetworkDataSource
import com.example.testweather.data.entity.Forecast
import com.example.testweather.data.entity.Location
import com.example.testweather.utils.Result
import java.util.*

interface WeatherDataSource : NetworkDataSource {
    /**
     * Performs GET request to the OpenWeatherMap API to fetch new [Forecast]
     */
    suspend fun request(location: Location, locale: Locale): Result<Forecast>
}