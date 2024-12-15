package com.pdgalvan.cryptotracker.core.network

import com.pdgalvan.cryptotracker.core.domain.NetworkError
import com.pdgalvan.cryptotracker.core.domain.Result
import retrofit2.Response

inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, NetworkError> {
    return when(response.code()) {
        in 200..299 -> {
            val body = response.body()
            if (body != null) {
                Result.Success(body)
            } else {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}