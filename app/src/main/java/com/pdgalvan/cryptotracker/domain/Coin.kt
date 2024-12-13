package com.pdgalvan.cryptotracker.domain

import java.math.BigDecimal

data class Coin(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val priceUsd: BigDecimal,
    val changePercent24Hr: BigDecimal,
)