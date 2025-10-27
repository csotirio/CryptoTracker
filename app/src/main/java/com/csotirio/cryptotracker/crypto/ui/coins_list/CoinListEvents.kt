package com.csotirio.cryptotracker.crypto.ui.coins_list

import com.csotirio.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvents {
    data class Error(val error: NetworkError): CoinListEvents
}