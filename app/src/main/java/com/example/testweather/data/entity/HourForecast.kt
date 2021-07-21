package com.example.testweather.data.entity

import androidx.annotation.Keep
import androidx.room.TypeConverters
import com.example.testweather.data.db.Converters

@Keep
data class HourForecast(
    var currentForecastId: Int = 0,
    @TypeConverters(Converters::class)
    var temperature: Temperature,
    val time: String,
    val imageId: Int
)