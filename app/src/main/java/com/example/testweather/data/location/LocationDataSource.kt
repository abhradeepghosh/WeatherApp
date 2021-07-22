package com.example.testweather.data.location

import com.example.testweather.base.NetworkDataSource
import com.example.testweather.data.entity.Location
import com.example.testweather.utils.Result

interface LocationDataSource : NetworkDataSource {
    suspend fun request(locationName: String): Result<List<Location>>
}