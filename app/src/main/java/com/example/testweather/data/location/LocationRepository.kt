package com.example.testweather.data.location

import com.example.testweather.data.entity.Location
import com.example.testweather.utils.Result

interface LocationRepository {
    /**
     * Performs GET request to the OpenCage API to fetch locations
     */
    suspend fun getLocationsByName(locationName: String): Result<List<Location>>
}