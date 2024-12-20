package com.pdgalvan.cryptotracker.domain

import java.math.BigDecimal

data class Coin(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val priceUsd: BigDecimal,
    val changePercent24Hr: BigDecimal,
    val supply: BigDecimal,
    val maxSupply: BigDecimal?,
    val marketCapUsd: BigDecimal,
    val volumeUsd24Hr: BigDecimal,
){
    fun priceVariation() = priceUsd * (changePercent24Hr / BigDecimal(100))
}