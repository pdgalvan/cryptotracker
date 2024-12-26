package com.pdgalvan.cryptotracker.domain.repository

import com.pdgalvan.cryptotracker.core.domain.NetworkError
import com.pdgalvan.cryptotracker.core.domain.Result
import com.pdgalvan.cryptotracker.domain.Coin
import com.pdgalvan.cryptotracker.domain.CoinPrice

interface CoinRepository {

    suspend fun getCoins(): Result<List<Coin>, NetworkError>

    suspend fun getCoin(id: String): Result<Coin, NetworkError>

    suspend fun getCoinPriceHistory(id: String): Result<List<CoinPrice>, NetworkError>
}