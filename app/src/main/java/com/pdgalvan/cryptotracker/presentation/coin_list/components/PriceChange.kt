package com.pdgalvan.cryptotracker.presentation.coin_list.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.pdgalvan.cryptotracker.presentation.model.DisplayableNumber
import com.pdgalvan.cryptotracker.presentation.model.toCurrency
import com.pdgalvan.cryptotracker.presentation.model.toPercentage
import com.pdgalvan.cryptotracker.ui.theme.CryptoTrackerTheme
import com.pdgalvan.cryptotracker.ui.theme.positiveGreenColor
import java.math.BigDecimal

@Composable
fun PriceChange(
    percentage: DisplayableNumber,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
) {
    val color = if (percentage.value > BigDecimal.ZERO) {
        MaterialTheme.colorScheme.positiveGreenColor
    } else {
        MaterialTheme.colorScheme.error
    }
    Text(
        modifier = modifier,
        text = percentage.toPercentage(),
        color = color,
        style = style
    )

}

@Composable
fun PriceChange(
    priceVariation: DisplayableNumber,
    percentage: DisplayableNumber,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val color = if (percentage.value > BigDecimal.ZERO) {
        MaterialTheme.colorScheme.positiveGreenColor
    } else {
        MaterialTheme.colorScheme.error
    }

    Text(
        modifier = modifier,
        text = "${priceVariation.toCurrency()}(${percentage.toPercentage()})",
        color = color,
        style = style
    )

}

@Preview
@Composable
fun PriceChangePreview() {
    CryptoTrackerTheme {
        PriceChange(
            percentage = DisplayableNumber(
                value = BigDecimal(-23.34),
                formatted = "-23.34"
            )
        )
    }
}

