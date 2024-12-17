package com.pdgalvan.cryptotracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdgalvan.cryptotracker.presentation.coin_detail.CoinDetailRoot
import com.pdgalvan.cryptotracker.presentation.coin_list.CoinListRoot

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = CoinList,
    ) {
        composable<CoinList> {
            CoinListRoot(
                modifier = modifier,
                onCoinClick = { id ->
                    navController.navigate(CoinDetail(id))
                }
            )
        }
        composable<CoinDetail> {
            CoinDetailRoot(
                modifier = modifier,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
