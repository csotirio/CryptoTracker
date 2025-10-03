package com.csotirio.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csotirio.cryptotracker.crypto.ui.coins_list.CoinListViewModel
import com.csotirio.cryptotracker.crypto.ui.coins_list.components.CoinListScreenComposable
import com.csotirio.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    CoinListScreenComposable(
                        uiModel = uiState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}