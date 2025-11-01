package com.csotirio.cryptotracker.crypto.ui.coins_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csotirio.cryptotracker.core.domain.util.onError
import com.csotirio.cryptotracker.core.domain.util.onSuccess
import com.csotirio.cryptotracker.crypto.ui.mapper.toCoinUiModel
import com.csotirio.cryptotracker.crypto.ui.model.CoinListUiModel
import com.csotirio.cryptotracker.crypto.ui.model.CoinUiModel
import com.csotirio.cryptotracker.ui.coin_details.DataPoint
import com.csotirio.cryptotracker.usecase.GetCoinsPriceHistoryUseCase
import com.csotirio.cryptotracker.usecase.GetCoinsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getCoinsPriceHistoryUseCase: GetCoinsPriceHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinListUiModel())
    val uiState = _uiState
        .onStart { loadCoins() }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = CoinListUiModel()
        )

    private val _events = Channel<CoinListEvents>()
    val events = _events.receiveAsFlow()

    fun onUserAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick ->
                updateSelectedCoin(action.coinUi)

        }
    }

    private fun updateSelectedCoin(coinUi: CoinUiModel) {
        _uiState.update {
            it.copy(
                selectedCoin = coinUi
            )
        }
        coinUi.id?.let{ id ->
            viewModelScope.launch {
                getCoinsPriceHistoryUseCase(
                    coinId = id,
                    start = ZonedDateTime.now().minusDays(5),
                    end = ZonedDateTime.now()
                )
                    .onSuccess { history ->
                        val dataPoints = history
                            .sortedBy { it.dateTime }
                            .map {
                                DataPoint(
                                    x = it.dateTime.hour.toFloat(),
                                    y = it.priceUsd.toFloat(),
                                    xLabel = DateTimeFormatter
                                        .ofPattern("ha\nM/d")
                                        .format(it.dateTime)
                                )
                            }

                        _uiState.update {
                            it.copy(
                                selectedCoin = it.selectedCoin?.copy(
                                    coinPriceHistory = dataPoints
                                )
                            )
                        }
                    }
                    .onError { error ->
                        _events.send(CoinListEvents.Error(error))
                    }
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getCoinsUseCase()
                .onSuccess { coins ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { coin -> coin.toCoinUiModel() }
                        )
                    }
                }
                .onError { error ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = false
                        )
                    }
                    _events.send(CoinListEvents.Error(error))
                }
        }
    }
}