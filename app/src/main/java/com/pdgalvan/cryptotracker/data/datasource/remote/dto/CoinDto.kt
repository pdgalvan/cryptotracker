package com.pdgalvan.cryptotracker.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val priceUsd: Double,
    val changePercent24Hr: Double,
)
