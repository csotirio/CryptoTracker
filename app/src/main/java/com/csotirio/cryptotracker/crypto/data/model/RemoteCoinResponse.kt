package com.csotirio.cryptotracker.crypto.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCoins(
    val data: List<RemoteCoin>? = null
)

@Serializable
data class RemoteCoin(
    val id: String? = null,
    val rank: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val marketCapUsd: Double? = null,
    val priceUsd: Double? = null,
    val changePercent24Hr: Double? = null
)