package com.android.cryptocurrency.domain.use_case.get_coin

import com.android.cryptocurrency.common.Resource
import com.android.cryptocurrency.data.remote.dto.toCoin
import com.android.cryptocurrency.data.remote.dto.toCoinDetail
import com.android.cryptocurrency.domain.model.Coin
import com.android.cryptocurrency.domain.model.CoinDetail
import com.android.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinUseCase(private val repository: CoinRepository) {

    operator fun invoke(coinId : String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading<CoinDetail>())
        try {
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "Error"))
        }catch (e: IOException) {
            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "IO Error"))
        }
    }
}