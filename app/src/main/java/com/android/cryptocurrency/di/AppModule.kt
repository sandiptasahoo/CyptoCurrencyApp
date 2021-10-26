package com.android.cryptocurrency.di

import com.android.cryptocurrency.common.Constants
import com.android.cryptocurrency.data.remote.CoinPaprikaApi
import com.android.cryptocurrency.data.repository.CoinRepositoryImpl
import com.android.cryptocurrency.domain.repository.CoinRepository
import com.android.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import com.android.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import com.android.cryptocurrency.presentation.coin_details.CoinDetailsViewModel
import com.android.cryptocurrency.presentation.coin_list.CoinListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coinsUseCase = module {
    single { GetCoinsUseCase(get()) }
}

val coinUseCase = module {
    single { GetCoinUseCase(get()) }
}

val coinListViewModelModule = module {
    viewModel { CoinListViewModel(get()) }
}

val coinDetailsViewModelModule = module {
    viewModel { (coinId: String) -> CoinDetailsViewModel(coinId, get()) }
}

val repositoryModule = module {
    single<CoinRepository> { CoinRepositoryImpl(get()) }
}

val apiModule = module {
    fun provideCoinPaprikaApi(retrofit: Retrofit): CoinPaprikaApi {
        return retrofit.create(CoinPaprikaApi::class.java)
    }
    single { provideCoinPaprikaApi(get()) }
}

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    factory { provideGson() }
    factory { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}