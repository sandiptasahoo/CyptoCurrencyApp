package com.android.cryptocurrency.data.remote

import com.android.cryptocurrency.data.remote.dto.CoinDetailDto
import com.android.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(
        @Path("coinId") coniId: String,
    ): CoinDetailDto
}