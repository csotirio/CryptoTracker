package com.csotirio.cryptotracker.crypto.domain

import com.csotirio.cryptotracker.core.domain.util.NetworkError
import com.csotirio.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<CoinDomainModel>, NetworkError>
}