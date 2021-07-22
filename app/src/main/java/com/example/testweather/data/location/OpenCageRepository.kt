package com.example.testweather.data.location

import com.example.testweather.data.entity.Location
import com.example.testweather.utils.Result

class OpenCageRepository(
    private val locationDataSource: LocationDataSource
): LocationRepository {
    override suspend fun getLocationsByName(locationName: String): Result<List<Location>> {
        return locationDataSource.request(locationName)
    }
}