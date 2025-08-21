package com.csotirio.cryptotracker.crypto.ui.model

import androidx.annotation.DrawableRes
import com.csotirio.cryptotracker.R
import com.csotirio.cryptotracker.common.model.DisplayableNumberUiModel

data class CoinUiModel(
    val id: String? = null,
    val rank: Int? = null,
    val name: String? = null,
    val symbol: String? = null,
    val marketCapUsd: DisplayableNumberUiModel? = null,
    val priceUsd: DisplayableNumberUiModel? = null,
    val changePercent24Hr: DisplayableNumberUiModel? = null,
    @DrawableRes val iconRes: Int? = null
)

internal val previewCoinUiModel = CoinUiModel(
    id = "1",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = DisplayableNumberUiModel(
        value = 123456789.0,
        formattedValue = "$123,456,789"
    ),
    priceUsd = DisplayableNumberUiModel(
        value = 34567.0,
        formattedValue = "$34,567"
    ),
    changePercent24Hr = DisplayableNumberUiModel(
        value = 123.0,
        formattedValue = "+123%"
    ),
    iconRes = R.drawable.btc
)