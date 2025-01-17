package com.pdgalvan.cryptotracker.data.datasource.remote

import com.pdgalvan.cryptotracker.data.datasource.remote.dto.CoinPriceHistoryDto
import com.pdgalvan.cryptotracker.data.datasource.remote.dto.CoinResponseDto
import com.pdgalvan.cryptotracker.data.datasource.remote.dto.CoinsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET("/v2/assets")
    suspend fun getCoins(): Response<CoinsResponseDto>

    @GET("/v2/assets/{id}")
    suspend fun getCoin(@Path("id") id: String): Response<CoinResponseDto>

    @GET("/v2/assets/{id}/history?interval=d1")
    suspend fun getCoinPriceHistory(@Path("id") id: String): Response<CoinPriceHistoryDto>
}