package com.csotirio.cryptotracker.crypto.domain

import com.csotirio.cryptotracker.core.domain.util.NetworkError
import com.csotirio.cryptotracker.core.domain.util.Result
import com.csotirio.cryptotracker.crypto.domain.models.CoinDomainModel
import com.csotirio.cryptotracker.crypto.domain.models.CoinPriceDomainModel
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<CoinDomainModel>, NetworkError>

    suspend fun getCoinPriceHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPriceDomainModel>, NetworkError>
}