package com.csotirio.cryptotracker.crypto.ui.coins_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.csotirio.cryptotracker.core.ui.util.Dimens.dimen8dp
import com.csotirio.cryptotracker.crypto.ui.coins_list.CoinListAction
import com.csotirio.cryptotracker.crypto.ui.model.CoinListUiModel
import com.csotirio.cryptotracker.crypto.ui.model.previewCoinUiModel
import com.csotirio.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListScreenComposable(
    uiModel: CoinListUiModel,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiModel.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimen8dp)
        ) {
            items(
                items = uiModel.coins
            ) { coinUi ->
                CoinListItem(
                    uiModel = coinUi,
                    onCoinClicked = {
                        onAction(CoinListAction.OnCoinClick(coinUi))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}

@PreviewLightDark()
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreenComposable(
            uiModel = CoinListUiModel(
                isLoading = false,
                coins = (1..100).map {
                    previewCoinUiModel.copy(
                        id = it.toString()
                    )
                }
            ),
            onAction = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}