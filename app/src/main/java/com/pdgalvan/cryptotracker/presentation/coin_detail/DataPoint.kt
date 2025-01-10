package com.pdgalvan.cryptotracker.presentation.coin_detail

import com.pdgalvan.cryptotracker.domain.CoinPrice
import java.time.format.DateTimeFormatter

data class DataPoint(
    val x: Float,
    val y: Float,
    val xLabel: String,
)

fun CoinPrice.toDataPoint() = DataPoint(
    x = time.hour.toFloat(),
    y = priceUsd.toFloat(),
    xLabel = DateTimeFormatter
        .ofPattern("ha\nM/d")
        .format(time)
)

fun List<CoinPrice>.toDataPoints() = map { it.toDataPoint() }

