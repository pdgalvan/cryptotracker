package com.pdgalvan.cryptotracker.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pdgalvan.cryptotracker.core.presentation.ObserveAsEvent
import com.pdgalvan.cryptotracker.core.presentation.toString
import com.pdgalvan.cryptotracker.presentation.coin_list.components.CoinListItem
import com.pdgalvan.cryptotracker.presentation.coin_list.components.previewCoin
import com.pdgalvan.cryptotracker.presentation.coin_list.components.previewNegativeCoin
import com.pdgalvan.cryptotracker.presentation.model.CoinUI
import com.pdgalvan.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CoinListRoot(
    onCoinClick: (id: String) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    val state by viewModel.state.collectAsState()
    CoinListScreen(
        state = state,
        events = viewModel.events,
        modifier = modifier,
        onCoinClick = onCoinClick,
    )
}

@Composable
fun CoinListScreen(
    state: CoinListState,
    events: Flow<CoinListEvent>,
    onCoinClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    ObserveAsEvent(
        events = events,
        onEvent = { event ->
            when (event) {
                is CoinListEvent.Error -> {
                    Toast.makeText(
                        context,
                        event.error.toString(context),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    )
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        CoinListContent(
            coins = state.coins,
            onCoinClick = onCoinClick,
            modifier = modifier
        )
    }
}

@Composable
fun CoinListContent(
    coins: List<CoinUI>,
    onCoinClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(coins) { coin ->
            CoinListItem(
                coin = coin,
                onItemClick = { onCoinClick(coin.id) },
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider()
        }
    }
}

val previewCoins = listOf(
    previewCoin,
    previewNegativeCoin,
    previewCoin,
    previewNegativeCoin,
)

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(
            CoinListState(coins = previewCoins),
            events = emptyFlow(),
            onCoinClick = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

