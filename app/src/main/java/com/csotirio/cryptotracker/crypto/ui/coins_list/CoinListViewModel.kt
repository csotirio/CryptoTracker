package com.csotirio.cryptotracker.crypto.ui.coins_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csotirio.cryptotracker.core.domain.util.onError
import com.csotirio.cryptotracker.core.domain.util.onSuccess
import com.csotirio.cryptotracker.crypto.ui.mapper.toCoinUiModel
import com.csotirio.cryptotracker.crypto.ui.model.CoinListUiModel
import com.csotirio.cryptotracker.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinListUiModel())
    val uiState = _uiState
        .onStart { loadCoins() }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = CoinListUiModel()
        )

    fun onUserAction(action: CoinListAction){
        when(action){
            is CoinListAction.OnCoinClick -> TODO()
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
                .onError {
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }
}