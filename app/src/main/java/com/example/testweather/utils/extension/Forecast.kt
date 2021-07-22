package com.example.testweather.utils.extension

import com.example.testweather.data.entity.FavouriteForecast
import com.example.testweather.data.entity.Forecast
import com.example.testweather.data.entity.Location

fun Forecast.toLocation() = Location(locationName, longitude, latitude)

fun Forecast.toFavouriteForecast() = FavouriteForecast(
    id = id,
    temperature = currentForecast.temperature,
    description = currentForecast.description,
    locationName = locationName,
    imageId = currentForecast.imageId
)