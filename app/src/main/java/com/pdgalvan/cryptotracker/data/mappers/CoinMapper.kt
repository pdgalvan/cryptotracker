package com.pdgalvan.cryptotracker.data.mappers

import com.pdgalvan.cryptotracker.data.datasource.remote.dto.CoinDto
import com.pdgalvan.cryptotracker.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    priceUsd = priceUsd.toBigDecimal(),
    changePercent24Hr = changePercent24Hr.toBigDecimal(),
    supply = supply.toBigDecimal(),
    maxSupply = maxSupply?.toBigDecimal(),
    marketCapUsd = marketCapUsd.toBigDecimal(),
    volumeUsd24Hr = volumeUsd24Hr.toBigDecimal(),
)

fun List<CoinDto>.toCoins() = map { it.toCoin() }