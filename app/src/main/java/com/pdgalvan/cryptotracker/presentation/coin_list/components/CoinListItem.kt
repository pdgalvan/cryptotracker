package com.pdgalvan.cryptotracker.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pdgalvan.cryptotracker.domain.Coin
import com.pdgalvan.cryptotracker.presentation.model.CoinUI
import com.pdgalvan.cryptotracker.presentation.model.toCoinUI
import com.pdgalvan.cryptotracker.presentation.model.toCurrency
import com.pdgalvan.cryptotracker.ui.theme.CryptoTrackerTheme
import java.math.BigDecimal

@Composable
fun CoinListItem(
    coin: CoinUI,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(coin.iconRes),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = coin.name,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = coin.symbol,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = coin.priceUsd.toCurrency(),
                color = MaterialTheme.colorScheme.onSurface

            )
            PriceChange(
                percentage = coin.changePercent24Hr
            )
        }
    }
}


internal val previewCoin = Coin(
    id = "bitcoin",
    rank = 3,
    symbol = "BTC",
    name = "Bitcoin",
    priceUsd = BigDecimal(96723.3745),
    changePercent24Hr = BigDecimal(0.843),
    supply = BigDecimal(17193925.0000),
    maxSupply = BigDecimal(21000000.0000),
    marketCapUsd = BigDecimal(119150835874.46200),
    volumeUsd24Hr = BigDecimal(2927959461.17503),
).toCoinUI()

internal val previewNegativeCoin = Coin(
    id = "ethereum",
    rank = 3,
    symbol = "ETH",
    name = "Ethereum",
    priceUsd = BigDecimal(68392.1139),
    changePercent24Hr = BigDecimal(-1.143),
    supply = BigDecimal(17193925.0000),
    maxSupply = BigDecimal(21000000.0000),
    marketCapUsd = BigDecimal(119150835874.46200),
    volumeUsd24Hr = BigDecimal(2927959461.17503),
).toCoinUI()

@PreviewLightDark
@Composable
fun CoinListItemPreview(){
    CryptoTrackerTheme {
        CoinListItem(
            coin = previewCoin,
            onItemClick =  { },
            modifier = Modifier.padding(5.dp).background(
                MaterialTheme.colorScheme.surfaceContainer
            )
        )
    }
}

@PreviewLightDark
@Composable
fun CoinListNegativeItemPreview(){
    CryptoTrackerTheme {
        CoinListItem(
            coin = previewNegativeCoin,
            onItemClick =  { },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.surfaceContainer
            )
        )
    }
}
