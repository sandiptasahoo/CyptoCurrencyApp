package com.android.cryptocurrency.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptocurrency.common.Resource
import com.android.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinDetailsViewModel(
    coinId: String,
    private val getCoinUseCase: GetCoinUseCase,
    ) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        /*savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoinDetails(it)
        }*/
        getCoinDetails(coinId)
    }

    private fun getCoinDetails(coinId : String) {
        getCoinUseCase(coinId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = CoinDetailsState(coinDetail = resource.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailsState(error = resource.message ?: "Error occured!")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}