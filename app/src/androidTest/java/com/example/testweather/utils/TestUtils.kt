package com.example.testweather.utils

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testweather.data.AppSettings
import com.example.testweather.data.AppSettingsImpl
import com.example.testweather.data.db.AppDataBase
import com.example.testweather.data.location.LocationDataSource
import com.example.testweather.data.location.LocationRepository
import com.example.testweather.data.location.OpenCageDataSource
import com.example.testweather.data.location.OpenCageRepository
import com.example.testweather.data.network.LocationApi
import com.example.testweather.data.network.WeatherApi
import com.example.testweather.data.weather.OpenWeatherMapDataSource
import com.example.testweather.data.weather.OpenWeatherMapRepository
import com.example.testweather.data.weather.WeatherDataSource
import com.example.testweather.ui.WeatherViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [Context] object used for tests
 */
val context: Context
    get() = InstrumentationRegistry.getInstrumentation().targetContext

/**
 * [AppDataBase] object used for tests
 */
val appDatabase: AppDataBase
    get() = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
        .allowMainThreadQueries()
        .build()

/**
 * [WeatherApi] object used for tests
 */
val weatherApi: WeatherApi
    get() = Retrofit.Builder()
        .baseUrl(OPEN_WEATHER_MAP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

/**
 * [LocationApi] object used for tests
 */
val locationApi: LocationApi
    get() = Retrofit.Builder()
        .baseUrl(OPEN_CAGE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LocationApi::class.java)

/**
 * [OpenWeatherMapDataSource] object used for tests
 */
val weatherDataSource: WeatherDataSource
    get() = OpenWeatherMapDataSource(weatherApi)

/**
 * [OpenWeatherMapRepository] object used for tests
 */
val weatherRepository: OpenWeatherMapRepository
    get() = OpenWeatherMapRepository(weatherDataSource, appDatabase, appSettings)

val locationDataSource: LocationDataSource
    get() = OpenCageDataSource(locationApi)
/**
 * [LocationRepository] object used for tests
 */
val locationRepository: LocationRepository
    get() = OpenCageRepository(locationDataSource)

/**
 * [AppSettings] object used for tests
 */
val appSettings: AppSettings
    get() = AppSettingsImpl(context)

/**
 * [WeatherViewModel] object used for tests
 */
val weatherViewModel: WeatherViewModel
    get() = WeatherViewModel(weatherRepository, appSettings)