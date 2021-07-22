package com.example.testweather.data.weather

import com.example.testweather.data.entity.FavouriteForecast
import com.example.testweather.data.entity.Forecast
import com.example.testweather.data.entity.Location
import com.example.testweather.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getForecastById(id: Int): Result<Forecast>

    suspend fun getForecastByLocation(location: Location): Result<Forecast>

    suspend fun getUpdatedForecast(forecast: Forecast): Result<Forecast>

    suspend fun getObservableCurrentForecast(): Flow<Result<Forecast>>

    suspend fun getFavouriteForecasts(): Result<List<FavouriteForecast>>

    suspend fun updateFavouriteForecasts()

    suspend fun starEvent(forecast: Forecast, isStarred: Boolean)

    suspend fun isEmpty(): Boolean
}