package com.example.testweather.data.entity

import androidx.annotation.Keep
import androidx.room.TypeConverters
import com.example.testweather.data.db.Converters

@Keep
data class DayForecast(
    @TypeConverters(Converters::class)
    var highestTemp: Temperature,
    @TypeConverters(Converters::class)
    var lowestTemp: Temperature,
    val day: String,
    val date: String,
    @TypeConverters(Converters::class)
    val wind: Wind,
    @TypeConverters(Converters::class)
    val pressure: Pressure,
    val humidity: Int,
    val imageId: Int
)
