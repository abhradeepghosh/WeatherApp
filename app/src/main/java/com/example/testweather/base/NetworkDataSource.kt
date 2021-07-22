package com.example.testweather.base

import com.example.testweather.utils.Result
import com.example.testweather.utils.Result.Error
import com.example.testweather.utils.Result.Success
import com.example.testweather.utils.exception.BadServerResponse
import com.example.testweather.utils.exception.NoConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

interface NetworkDataSource {
    suspend fun <T, R, P> safeApiCall(
        call: suspend () -> Response<T>,
        params: P,
        transform: (T, P) -> R
    ): Result<R> {
        try {
            val response = withContext(Dispatchers.IO) { call.invoke() }
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    withContext(Dispatchers.Default) {
                        Success(transform(body, params))
                    }
                } else {
                    Error(BadServerResponse)
                }
            } else {
                Error(BadServerResponse)
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return Error(NoConnection)
        } catch (unknown: Exception) {
            unknown.printStackTrace()
            return Error(BadServerResponse)
        }
    }
}