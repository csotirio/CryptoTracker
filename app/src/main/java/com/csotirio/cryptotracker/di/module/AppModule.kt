package com.csotirio.cryptotracker.di.module

import com.csotirio.cryptotracker.core.data.networking.HttpClientFactory
import com.csotirio.cryptotracker.crypto.data.networking.CoinDatasourceImpl
import com.csotirio.cryptotracker.crypto.domain.CoinDataSource
import com.csotirio.cryptotracker.crypto.ui.coins_list.CoinListViewModel
import com.csotirio.cryptotracker.usecase.GetCoinsPriceHistoryUseCase
import com.csotirio.cryptotracker.usecase.GetCoinsUseCase
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::CoinDatasourceImpl).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
    factoryOf(::GetCoinsUseCase)
    factoryOf(::GetCoinsPriceHistoryUseCase)
}