@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.csotirio.cryptotracker.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csotirio.cryptotracker.core.ui.util.ObserveAsEvents
import com.csotirio.cryptotracker.core.ui.util.toString
import com.csotirio.cryptotracker.crypto.ui.coins_list.CoinListAction
import com.csotirio.cryptotracker.crypto.ui.coins_list.CoinListEvents
import com.csotirio.cryptotracker.crypto.ui.coins_list.CoinListViewModel
import com.csotirio.cryptotracker.crypto.ui.coins_list.components.CoinListScreenComposable
import com.csotirio.cryptotracker.ui.coin_details.composable.CoinDetailScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is CoinListEvents.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreenComposable(
                    uiModel = uiState,
                    onAction = { action ->
                        viewModel.onUserAction(action)
                        when (action) {
                            is CoinListAction.OnCoinClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(state = uiState)
            }
        },
        modifier = modifier
    )
}