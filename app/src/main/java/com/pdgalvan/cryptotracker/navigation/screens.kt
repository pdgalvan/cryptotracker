package com.pdgalvan.cryptotracker.navigation

import kotlinx.serialization.Serializable

@Serializable
data object CoinList

@Serializable
data class CoinDetail(val id: String)