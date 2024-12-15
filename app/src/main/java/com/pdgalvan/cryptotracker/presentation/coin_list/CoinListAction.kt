package com.pdgalvan.cryptotracker.presentation.coin_list

import com.pdgalvan.cryptotracker.presentation.model.CoinUI

interface CoinListAction {
    data class onCoinClick(val coinUI: CoinUI) : CoinListAction
}