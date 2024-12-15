package com.pdgalvan.cryptotracker.presentation.coin_list

import com.pdgalvan.cryptotracker.core.domain.NetworkError

sealed interface CoinListEvent{
    data class Error(val error: NetworkError) : CoinListEvent
}