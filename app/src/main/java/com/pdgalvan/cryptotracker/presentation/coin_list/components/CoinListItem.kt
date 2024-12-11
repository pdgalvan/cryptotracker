package com.pdgalvan.cryptotracker.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            painter = painterResource(coin.iconRes),
            contentDescription = null,
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


internal val previewIcon = Coin(
    id = "bitcoin",
    rank = 3,
    symbol = "BTC",
    name = "Bitcoin",
    priceUsd = BigDecimal(96723.3745),
    changePercent24Hr = BigDecimal(0.843),
).toCoinUI()

@PreviewLightDark
@Composable
fun CoinListItemPreview(){
    CryptoTrackerTheme {
        CoinListItem(
            coin = previewIcon,
            onItemClick =  { },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.surfaceContainer
            )
        )
    }
}
