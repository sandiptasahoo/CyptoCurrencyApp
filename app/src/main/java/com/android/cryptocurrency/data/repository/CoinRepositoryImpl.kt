package com.android.cryptocurrency.data.repository

import com.android.cryptocurrency.data.remote.CoinPaprikaApi
import com.android.cryptocurrency.data.remote.dto.CoinDetailDto
import com.android.cryptocurrency.data.remote.dto.CoinDto
import com.android.cryptocurrency.domain.repository.CoinRepository

//todo: Implement DI
class CoinRepositoryImpl(private val coinApi: CoinPaprikaApi) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinApi.getCoin(coinId)
    }
}