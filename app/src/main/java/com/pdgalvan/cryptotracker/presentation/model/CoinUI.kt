package com.pdgalvan.cryptotracker.presentation.model

import androidx.annotation.DrawableRes
import com.pdgalvan.cryptotracker.core.presentation.getDrawableIdForCoin
import com.pdgalvan.cryptotracker.domain.Coin
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

data class CoinUI(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    val priceVariation: DisplayableNumber,
    val supply: DisplayableNumber,
    val maxSupply: DisplayableNumber?,
    val marketCapUsd: DisplayableNumber,
    val volumeUsd24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
)

data class DisplayableNumber(
    val value: BigDecimal,
    val formatted: String,
)

fun Coin.toCoinUI() = CoinUI(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    priceUsd = priceUsd.toDisplayableNumber(),
    changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
    priceVariation = priceVariation().toDisplayableNumber(),
    supply = supply.toDisplayableNumber(),
    maxSupply = maxSupply?.toDisplayableNumber(),
    marketCapUsd = marketCapUsd.toDisplayableNumber(),
    volumeUsd24Hr = volumeUsd24Hr.toDisplayableNumber(),
    iconRes = getDrawableIdForCoin(symbol),
)

fun BigDecimal.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun DisplayableNumber.toPercentage(): String {
    val sign = if(this.value > BigDecimal.ZERO) "+" else ""
    return "$sign${this.formatted}%"
}

fun DisplayableNumber.toCurrency() = "$${this.formatted}"
