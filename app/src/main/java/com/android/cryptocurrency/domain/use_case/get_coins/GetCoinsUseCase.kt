package com.android.cryptocurrency.domain.use_case.get_coins

import com.android.cryptocurrency.common.Resource
import com.android.cryptocurrency.data.remote.dto.toCoin
import com.android.cryptocurrency.domain.model.Coin
import com.android.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinsUseCase(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading<List<Coin>>())
        try {
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(message = e.localizedMessage ?: "Error"))
        }catch (e: IOException) {
            emit(Resource.Error<List<Coin>>(message = e.localizedMessage ?: "IO Error"))
        }
    }
}