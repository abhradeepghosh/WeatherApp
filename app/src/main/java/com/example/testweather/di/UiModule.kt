package com.example.testweather.di

import com.example.testweather.data.entity.FavouriteForecast
import com.example.testweather.data.entity.Location
import com.example.testweather.ui.favourite.FavouriteAdapter
import com.example.testweather.ui.search.SearchAdapter
import com.example.testweather.ui.weather.DailyForecastAdapter
import com.example.testweather.ui.weather.HourlyForecastAdapter
import org.koin.dsl.module

val uiModule = module {
    factory { HourlyForecastAdapter() }
    factory { DailyForecastAdapter() }
    factory { (onLocationChange: (Location) -> Unit) -> SearchAdapter(onLocationChange) }
    factory { (onForecastChange: (FavouriteForecast) -> Unit) -> FavouriteAdapter(onForecastChange) }
}