package com.android.cryptocurrency

import android.app.Application
import com.android.cryptocurrency.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(retrofitModule, apiModule, repositoryModule,
                coinsUseCase, coinUseCase, coinListViewModelModule, coinDetailsViewModelModule))
        }
    }
}