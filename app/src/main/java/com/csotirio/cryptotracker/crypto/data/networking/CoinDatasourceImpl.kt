package com.csotirio.cryptotracker.crypto.data.networking

import com.csotirio.cryptotracker.core.data.networking.constructUrl
import com.csotirio.cryptotracker.core.data.networking.safeCall
import com.csotirio.cryptotracker.core.domain.util.NetworkError
import com.csotirio.cryptotracker.core.domain.util.Result
import com.csotirio.cryptotracker.core.domain.util.map
import com.csotirio.cryptotracker.crypto.data.mapper.toDomainModel
import com.csotirio.cryptotracker.crypto.data.model.RemoteCoins
import com.csotirio.cryptotracker.crypto.domain.CoinDataSource
import com.csotirio.cryptotracker.crypto.domain.CoinDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

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

}