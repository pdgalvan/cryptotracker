package com.pdgalvan.cryptotracker.domain.repository

import com.pdgalvan.cryptotracker.core.domain.NetworkError
import com.pdgalvan.cryptotracker.core.domain.Result
import com.pdgalvan.cryptotracker.domain.Coin

interface CoinRepository {

    suspend fun getCoins(): Result<List<Coin>, NetworkError>

    suspend fun getCoin(id: String): Result<Coin, NetworkError>
}