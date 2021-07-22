package com.example.testweather.data.location

import com.example.testweather.BuildConfig
import com.example.testweather.data.entity.Location
import com.example.testweather.data.network.LocationApi
import com.example.testweather.data.network.json.opencage.LocationResponse
import com.example.testweather.utils.Result

class OpenCageDataSource(private val locationApi: LocationApi) :
    LocationDataSource {
    companion object {
        const val OPEN_CAGE_API_KEY = BuildConfig.API_KEY_OPEN_CAGE
    }

    /**
     * Performs GET request to the OpenCage API to fetch locations by [locationName].
     */
    override suspend fun request(locationName: String): Result<List<Location>> {
        return safeApiCall(
            call = { locationApi.getLocationsByNameAsync(locationName, OPEN_CAGE_API_KEY) },
            params = Unit,
            transform = ::transformToLocationList
        )
    }

    /**
     * Converts [LocationResponse] to the list of [Location]
     */
    private fun transformToLocationList(
        locationResponse: LocationResponse,
        params: Unit
    ): List<Location> {
        return locationResponse.results.map {
            Location(it.formatted, it.geometry.lng, it.geometry.lat)
        }
    }
}