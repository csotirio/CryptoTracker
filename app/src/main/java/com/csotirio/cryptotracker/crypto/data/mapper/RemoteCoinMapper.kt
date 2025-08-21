package com.csotirio.cryptotracker.crypto.data.mapper

import com.csotirio.cryptotracker.crypto.data.model.RemoteCoin
import com.csotirio.cryptotracker.crypto.domain.CoinDomainModel

fun RemoteCoin.toDomainModel() = CoinDomainModel(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)
