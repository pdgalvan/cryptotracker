package com.pdgalvan.cryptotracker.presentation.coin_detail

import com.pdgalvan.cryptotracker.core.domain.NetworkError

interface CoinDetailEvent {
    data class Error(val error: NetworkError) : CoinDetailEvent
}