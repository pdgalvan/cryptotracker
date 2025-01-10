package com.pdgalvan.cryptotracker.data.mappers

import com.pdgalvan.cryptotracker.data.datasource.remote.dto.CoinPriceDto
import com.pdgalvan.cryptotracker.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinPriceDto.toCoinPrice() = CoinPrice(
    priceUsd = priceUsd.toBigDecimal(),
    time = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
)
