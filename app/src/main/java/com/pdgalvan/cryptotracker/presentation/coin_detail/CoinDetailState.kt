package com.pdgalvan.cryptotracker.presentation.coin_detail

import com.pdgalvan.cryptotracker.presentation.model.CoinUI

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinUI: CoinUI? = null,
    val dataPoints: List<DataPoint> = emptyList(),
)
