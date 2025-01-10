package com.pdgalvan.cryptotracker.presentation.coin_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdgalvan.cryptotracker.domain.CoinPrice
import com.pdgalvan.cryptotracker.ui.theme.CryptoTrackerTheme
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.random.Random


@Composable
fun LineChart(
    data: List<DataPoint>,
    //style: ChartStyle,
    modifier: Modifier = Modifier,
) {
    val maxYValue = data.maxOfOrNull { it.y } ?: 0F
    val totalPoints = data.size

    Canvas(
        modifier = modifier.fillMaxSize(),
    ) {
        val lineDistance = size.width / (totalPoints)
        var currentLineDistance = 0F + lineDistance
        data.forEachIndexed { index, dataPoint ->
            if (totalPoints >= index + 2) {
                drawLine(
                    start = Offset(
                        x = currentLineDistance,
                        y = calculateYCoordinate(
                            higherTransactionRateValue = maxYValue,
                            currentTransactionRate = dataPoint.y,
                            canvasHeight = size.height
                        )
                    ),
                    end = Offset(
                        x = currentLineDistance + lineDistance,
                        y = calculateYCoordinate(
                            higherTransactionRateValue = maxYValue,
                            currentTransactionRate = data[index + 1].y,
                            canvasHeight = size.height
                        )
                    ),
                    color = Color.Red,
                    strokeWidth = Stroke.DefaultMiter
                )
                currentLineDistance += lineDistance
            }
        }
    }
}

private fun calculateYCoordinate(
    higherTransactionRateValue: Float,
    currentTransactionRate: Float,
    canvasHeight: Float
): Float {
    val maxAndCurrentValueDifference = (higherTransactionRateValue - currentTransactionRate)
    val relativePercentageOfScreen = (canvasHeight / higherTransactionRateValue)
    return maxAndCurrentValueDifference * relativePercentageOfScreen
}

@Preview
@Composable
fun LineChartPreview() {
    CryptoTrackerTheme {
        val coinHistoryRandomized = remember {
            (1..20).map {
                CoinPrice(
                    priceUsd = BigDecimal(Random.nextFloat() * 100.0),
                    time = ZonedDateTime.now().plusHours(it.toLong()),
                )
            }
        }
        val dataPoints = remember { coinHistoryRandomized.toDataPoints() }
        LineChart(
            data = dataPoints,
            modifier = Modifier
                .width(700.dp)
                .height(300.dp)
                .background(Color.White)
        )
    }
}
