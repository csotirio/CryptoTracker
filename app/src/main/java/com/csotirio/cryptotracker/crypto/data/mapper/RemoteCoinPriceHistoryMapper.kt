package com.csotirio.cryptotracker.crypto.data.mapper

import com.csotirio.cryptotracker.crypto.data.model.RemoteCoinPriceHistoryResponse
import com.csotirio.cryptotracker.crypto.domain.models.CoinPriceDomainModel
import java.time.Instant
import java.time.ZoneId

fun RemoteCoinPriceHistoryResponse.RemoteCoinPrice.toDomain(): CoinPriceDomainModel =
    CoinPriceDomainModel(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )