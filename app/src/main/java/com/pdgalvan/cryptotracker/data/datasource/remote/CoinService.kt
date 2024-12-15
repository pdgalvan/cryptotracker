package com.pdgalvan.cryptotracker.data.datasource.remote

import com.pdgalvan.cryptotracker.data.datasource.remote.dto.CoinsResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CoinService  {

    @GET("/v2/assets")
    suspend fun getCoins() : Response<CoinsResponseDto>
}