package com.android.cryptocurrency.presentation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin_list_Screen")
    object CoinDetailsScreen : Screen("coin_details_Screen")
}
