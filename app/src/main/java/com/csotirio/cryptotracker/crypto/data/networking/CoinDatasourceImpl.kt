package com.csotirio.cryptotracker.crypto.data.networking

import com.csotirio.cryptotracker.core.data.networking.constructUrl
import com.csotirio.cryptotracker.core.data.networking.safeCall
import com.csotirio.cryptotracker.core.domain.util.NetworkError
import com.csotirio.cryptotracker.core.domain.util.Result
import com.csotirio.cryptotracker.core.domain.util.map
import com.csotirio.cryptotracker.crypto.data.mapper.toDomain
import com.csotirio.cryptotracker.crypto.data.mapper.toDomainModel
import com.csotirio.cryptotracker.crypto.data.model.RemoteCoinPriceHistoryResponse
import com.csotirio.cryptotracker.crypto.data.model.RemoteCoins
import com.csotirio.cryptotracker.crypto.domain.CoinDataSource
import com.csotirio.cryptotracker.crypto.domain.models.CoinDomainModel
import com.csotirio.cryptotracker.crypto.domain.models.CoinPriceDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class CoinDatasourceImpl(
    private val httpClient: HttpClient
) : CoinDataSource {

    override suspend fun getCoins(): Result<List<CoinDomainModel>, NetworkError> {
        return safeCall<RemoteCoins>{
            httpClient.get(
                urlString = constructUrl("/assets"),
            )
        }.map { response ->
            response.data?.map { it.toDomainModel() } ?: emptyList()
        }
    }
    override suspend fun getCoinPriceHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPriceDomainModel>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<RemoteCoinPriceHistoryResponse> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toDomain() }
        }
    }
}