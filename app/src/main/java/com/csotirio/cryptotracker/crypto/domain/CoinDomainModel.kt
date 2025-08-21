package com.csotirio.cryptotracker.crypto.domain

data class CoinDomainModel(
    val id: String? = null,
    val rank: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val marketCapUsd: Double? = null,
    val priceUsd: Double? = null,
    val changePercent24Hr: Double? = null
)