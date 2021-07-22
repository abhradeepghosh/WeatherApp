package com.example.testweather.utils

import com.example.testweather.R
import com.example.testweather.data.entity.CurrentForecast
import com.example.testweather.data.entity.DayForecast
import com.example.testweather.data.entity.Forecast
import com.example.testweather.data.entity.HourForecast
import com.example.testweather.data.entity.Pressure
import com.example.testweather.data.entity.Temperature
import com.example.testweather.data.entity.Wind
import java.util.*


/**
 * [Forecast] object used for tests
 */
val forecast: Forecast
    get() = Forecast(
        locationName = "Bangalore, India",
        longitude = 77.5946,
        latitude = 12.9716,
        timestamp = Calendar.getInstance(),
        wasOpenedLast = false,
        isFavourite = false,
        currentForecast = CurrentForecast(
            currentForecastId = 0,
            temperature = Temperature(21),
            description = "Broken clouds",
            feelsLike = Temperature(18),
            wind = Wind(5),
            pressure = Pressure(1022),
            humidity = 56,
            imageId = 2131230822
        ),
        hourForecastList = arrayListOf(
            HourForecast(0, Temperature(21), "15:00", 1),
            HourForecast(1, Temperature(19), "18:00", 2),
            HourForecast(2, Temperature(16), "21:00", 3),
            HourForecast(3, Temperature(15), "23:00", 4)
        ),
        dayForecastList = arrayListOf(
            DayForecast(
                highestTemp = Temperature(21),
                lowestTemp = Temperature(19),
                day = "Wednesday",
                date = "21.07",
                wind = Wind(5),
                pressure = Pressure(1033),
                humidity = 97,
                imageId = R.drawable.current_weather_rain_light
            )
        )
    )