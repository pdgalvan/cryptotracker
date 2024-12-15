package com.pdgalvan.cryptotracker.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.pdgalvan.cryptotracker.domain.Coin
import com.pdgalvan.cryptotracker.presentation.model.CoinUI

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUI> = emptyList(),
    val selectedCoin: Coin? = null,
)
