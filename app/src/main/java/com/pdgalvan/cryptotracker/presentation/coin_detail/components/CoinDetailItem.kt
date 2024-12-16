package com.pdgalvan.cryptotracker.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pdgalvan.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinDetailItem(
    label: String,
    description: String,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
        Text(
            text = description,
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailItemPreview() {
    CryptoTrackerTheme {
        CoinDetailItem(
            label = "Total supply",
            description = "99.999.999",
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}