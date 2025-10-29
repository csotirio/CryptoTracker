package com.csotirio.cryptotracker.usecase

import com.csotirio.cryptotracker.core.domain.util.NetworkError
import com.csotirio.cryptotracker.core.domain.util.Result
import com.csotirio.cryptotracker.crypto.domain.CoinDataSource
import com.csotirio.cryptotracker.crypto.domain.models.CoinPriceDomainModel
import java.time.ZonedDateTime

class GetCoinsPriceHistoryUseCase(
    private val dataSource: CoinDataSource
) {
    suspend operator fun invoke(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPriceDomainModel>, NetworkError> {
        return dataSource.getCoinPriceHistory(
            coinId = coinId,
            start = start,
            end = end
        )
    }
}