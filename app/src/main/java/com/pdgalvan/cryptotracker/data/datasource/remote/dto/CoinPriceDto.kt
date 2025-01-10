package com.pdgalvan.cryptotracker.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val priceUsd: Double,
    val time: Long,
)