package com.android.cryptocurrency.domain.repository

import com.android.cryptocurrency.data.remote.dto.CoinDetailDto
import com.android.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}