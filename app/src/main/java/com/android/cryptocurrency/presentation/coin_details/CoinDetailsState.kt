package com.android.cryptocurrency.presentation.coin_details

import com.android.cryptocurrency.domain.model.CoinDetail

data class CoinDetailsState(
    val coinDetail: CoinDetail? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
