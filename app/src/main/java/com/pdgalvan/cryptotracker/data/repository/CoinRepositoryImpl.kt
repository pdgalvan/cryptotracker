package com.pdgalvan.cryptotracker.data.repository

import com.pdgalvan.cryptotracker.core.domain.NetworkError
import com.pdgalvan.cryptotracker.core.domain.Result
import com.pdgalvan.cryptotracker.core.domain.map
import com.pdgalvan.cryptotracker.core.network.safeCall
import com.pdgalvan.cryptotracker.data.datasource.remote.CoinService
import com.pdgalvan.cryptotracker.data.mappers.toCoin
import com.pdgalvan.cryptotracker.data.mappers.toCoins
import com.pdgalvan.cryptotracker.domain.Coin
import com.pdgalvan.cryptotracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val coinService: CoinService) : CoinRepository {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall { coinService.getCoins() }.map { it.data.toCoins() }
    }

    override suspend fun getCoin(id: String): Result<Coin, NetworkError> {
        return safeCall { coinService.getCoin(id) }.map { it.data.toCoin() }
    }
}