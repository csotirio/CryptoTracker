package com.csotirio.cryptotracker.crypto.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCoinPriceHistoryResponse(
    val data: List<RemoteCoinPrice>
){
    @Serializable
    data class RemoteCoinPrice(
        val priceUsd: Double,
        val time: Long
    )
}
