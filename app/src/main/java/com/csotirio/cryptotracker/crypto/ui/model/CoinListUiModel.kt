package com.csotirio.cryptotracker.crypto.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class CoinListUiModel(
    val isLoading: Boolean = false,
    val coins: List<CoinUiModel> = emptyList(),
    val selectedCoin: CoinUiModel? = null,
)