package com.pdgalvan.cryptotracker.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdgalvan.cryptotracker.core.domain.onError
import com.pdgalvan.cryptotracker.core.domain.onSuccess
import com.pdgalvan.cryptotracker.domain.repository.CoinRepository
import com.pdgalvan.cryptotracker.presentation.model.toCoinUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CoinListState()
        )


    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.onCoinClick -> {

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository
                .getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUI() }
                        )
                    }
                }
                .onError {
                    _state.update  { it.copy(isLoading = false) }
                }
        }
    }
}