package com.pdgalvan.cryptotracker.presentation.coin_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pdgalvan.cryptotracker.R
import com.pdgalvan.cryptotracker.presentation.coin_detail.components.CoinDetailItem
import com.pdgalvan.cryptotracker.presentation.coin_list.components.PriceChange
import com.pdgalvan.cryptotracker.presentation.coin_list.components.previewCoin
import com.pdgalvan.cryptotracker.presentation.model.CoinUI
import com.pdgalvan.cryptotracker.presentation.model.toCurrency
import com.pdgalvan.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinDetailRoot(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()
    CoinDetailScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun CoinDetailScreen(
    state: CoinDetailState,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (state.coinUI != null) {
            CoinDetailContent(
                coinUI = state.coinUI,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun CoinDetailContent(
    coinUI: CoinUI,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = coinUI.name,
                onBack = { }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = coinUI.priceUsd.toCurrency(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(coinUI.iconRes),
                    contentDescription = coinUI.symbol,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
            PriceChange(
                percentage = coinUI.changePercent24Hr,
                priceVariation = coinUI.priceVariation,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .background(Color.Blue)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CoinDetailItem(
                    label = stringResource(R.string.coin_detail_label_mark_cap),
                    description = coinUI.name,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                )
                CoinDetailItem(
                    label = stringResource(R.string.coin_detail_label_volume_24hr),
                    description = coinUI.name,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CoinDetailItem(
                    label = stringResource(R.string.coin_detail_label_available_supply),
                    description = coinUI.name,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                )
                CoinDetailItem(
                    label = stringResource(R.string.coin_detail_label_total_supply),
                    description = coinUI.name,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    title: String,
    onBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
fun CoinDetailScreenPreview() {
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinDetailState(coinUI = previewCoin)
        )
    }
}
