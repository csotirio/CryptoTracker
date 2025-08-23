package com.csotirio.cryptotracker.crypto.ui.coins_list

import com.csotirio.cryptotracker.crypto.ui.model.CoinUiModel

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUiModel): CoinListAction
}