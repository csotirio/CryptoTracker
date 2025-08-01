package com.csotirio.cryptotracker.crypto.ui.mapper

import com.csotirio.cryptotracker.common.mapper.toDisplayableNumberUiModel
import com.csotirio.cryptotracker.crypto.domain.CoinDomainModel
import com.csotirio.cryptotracker.crypto.ui.model.CoinUiModel
import com.csotirio.cryptotracker.util.getDrawableIdForCoin

fun CoinDomainModel.toCoinUiModel() = CoinUiModel(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd?.toDisplayableNumberUiModel(),
    priceUsd = priceUsd?.toDisplayableNumberUiModel(),
    changePercent24Hr = changePercent24Hr?.toDisplayableNumberUiModel(),
    iconRes = symbol?.let{ getDrawableIdForCoin(symbol = it) }
)