package com.csotirio.cryptotracker.usecase

import com.csotirio.cryptotracker.core.domain.util.NetworkError
import com.csotirio.cryptotracker.core.domain.util.Result
import com.csotirio.cryptotracker.crypto.domain.CoinDataSource
import com.csotirio.cryptotracker.crypto.domain.models.CoinDomainModel

class GetCoinsUseCase(
    private val dataSource: CoinDataSource
) {
    suspend operator fun invoke(): Result<List<CoinDomainModel>, NetworkError>{
        return dataSource.getCoins()
    }
}