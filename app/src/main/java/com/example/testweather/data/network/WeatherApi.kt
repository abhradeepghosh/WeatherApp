package com.example.testweather.data.network

import com.example.testweather.data.network.json.openweathermap.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/onecall")
    suspend fun getForecast(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("APPID") APPID: String,
        @Query("lang") language: String,
        @Query("units") units: String
    ): Response<WeatherResponse>
}