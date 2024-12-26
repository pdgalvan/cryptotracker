package com.pdgalvan.cryptotracker.presentation.coin_detail

import com.pdgalvan.cryptotracker.domain.CoinPrice
import com.pdgalvan.cryptotracker.presentation.model.CoinUI

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinUI: CoinUI? = null,
    val coinPrices: List<CoinPrice> = emptyList(),
)
