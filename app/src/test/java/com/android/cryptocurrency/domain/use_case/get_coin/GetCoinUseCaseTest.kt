package com.android.cryptocurrency.domain.use_case.get_coin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.cryptocurrency.common.Resource
import com.android.cryptocurrency.data.repository.CoinRepositoryImpl
import com.android.cryptocurrency.domain.model.CoinDetail
import com.android.cryptocurrency.domain.repository.CoinRepository
import com.android.cryptocurrency.presentation.coin_details.CoinDetailsState
import com.android.cryptocurrency.utils.TestCoroutineRule
import org.junit.Assert.*

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

import org.mockito.Mock
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class GetCoinUseCaseTest  {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository : CoinRepository

    @Mock
    private lateinit var resource: Resource<CoinDetail>

    @Test
    fun `fetch coin details`() = runBlockingTest{
        val actual = mutableListOf<CoinDetailsState>()
        flow<Resource<CoinDetail>> {
            emit(Resource.Success<CoinDetail>(CoinDetail("id","Name", "Desc", "SYM", 1, true, null, null)))
        }.collect {
            actual.add(CoinDetailsState(coinDetail = it.data))
        }
        assertTrue(actual.first().coinDetail is CoinDetail)
    }

}