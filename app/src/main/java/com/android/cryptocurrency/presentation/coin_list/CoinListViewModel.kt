package com.android.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptocurrency.common.Resource
import com.android.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinListViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = resource.message ?: "Error occured!")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}