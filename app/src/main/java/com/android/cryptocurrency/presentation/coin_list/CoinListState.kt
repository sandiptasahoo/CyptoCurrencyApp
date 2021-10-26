package com.android.cryptocurrency.presentation.coin_list

import com.android.cryptocurrency.domain.model.Coin

data class CoinListState(
    val coins: List<Coin> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
