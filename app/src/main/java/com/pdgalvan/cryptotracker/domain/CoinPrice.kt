package com.pdgalvan.cryptotracker.domain

import java.math.BigDecimal
import java.time.ZonedDateTime

data class CoinPrice(
    val price: BigDecimal,
    val time: ZonedDateTime,
)
