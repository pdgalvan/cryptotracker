package com.pdgalvan.cryptotracker.core.network

import com.pdgalvan.cryptotracker.core.domain.NetworkError
import com.pdgalvan.cryptotracker.core.domain.Result
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import retrofit2.Response
import java.net.UnknownHostException

import kotlin.coroutines.coroutineContext


suspend inline fun<reified T> safeCall(
    execute: () -> Response<T>
) : Result<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnknownHostException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}