package com.example.testweather.di

import com.example.testweather.data.AppSettings
import com.example.testweather.data.AppSettingsImpl
import com.example.testweather.data.db.DatabaseFactory
import com.example.testweather.data.location.LocationDataSource
import com.example.testweather.data.location.LocationRepository
import com.example.testweather.data.location.OpenCageDataSource
import com.example.testweather.data.location.OpenCageRepository
import com.example.testweather.data.weather.OpenWeatherMapDataSource
import com.example.testweather.data.weather.OpenWeatherMapRepository
import com.example.testweather.data.weather.WeatherDataSource
import com.example.testweather.data.weather.WeatherRepository
import com.example.testweather.ui.WeatherViewModel
import com.example.testweather.ui.favourite.FavouriteViewModel
import com.example.testweather.ui.search.SearchViewModel
import com.example.testweather.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    single<LocationDataSource> { OpenCageDataSource(get()) }
    single<LocationRepository> { OpenCageRepository(get()) }
    single<WeatherDataSource> { OpenWeatherMapDataSource(get()) }
    single<WeatherRepository> { OpenWeatherMapRepository(get(), get(), get()) }
    single<AppSettings> { AppSettingsImpl(androidApplication()) }
    single { DatabaseFactory.create(androidApplication()) }
}