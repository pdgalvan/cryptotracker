package com.pdgalvan.cryptotracker.domain

import java.math.BigDecimal
import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: BigDecimal,
    val time: ZonedDateTime,
)
