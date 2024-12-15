package com.pdgalvan.cryptotracker.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>,
)