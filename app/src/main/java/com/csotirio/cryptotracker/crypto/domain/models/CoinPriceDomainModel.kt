package com.csotirio.cryptotracker.crypto.domain.models

import java.time.ZonedDateTime

data class CoinPriceDomainModel(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)
