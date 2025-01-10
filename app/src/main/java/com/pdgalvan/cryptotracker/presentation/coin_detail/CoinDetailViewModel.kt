package com.pdgalvan.cryptotracker.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.pdgalvan.cryptotracker.core.domain.onError
import com.pdgalvan.cryptotracker.core.domain.onSuccess
import com.pdgalvan.cryptotracker.domain.repository.CoinRepository
import com.pdgalvan.cryptotracker.navigation.CoinDetail
import com.pdgalvan.cryptotracker.presentation.model.toCoinUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: CoinRepository,
) : ViewModel() {
    private val coinDetail = savedStateHandle.toRoute<CoinDetail>()

    private val _state = MutableStateFlow(CoinDetailState())
    val state = _state
        .onStart { loadCoinDetail() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CoinDetailState()
        )

    private val _events = Channel<CoinDetailEvent>()
    val events = _events.receiveAsFlow()


    private fun loadCoinDetail() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository
                .getCoin(coinDetail.id)
                .onSuccess { coin ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coinUI = coin.toCoinUI()
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinDetailEvent.Error(error))
                }

            repository
                .getCoinPriceHistory(coinDetail.id)
                .onSuccess { coinPrices ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            dataPoints = coinPrices.toDataPoints(),
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinDetailEvent.Error(error))
                 }
        }
    }
}