package com.pdgalvan.cryptotracker.presentation.coin_detail

import androidx.lifecycle.ViewModel
import com.pdgalvan.cryptotracker.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state = _state.asStateFlow()
}