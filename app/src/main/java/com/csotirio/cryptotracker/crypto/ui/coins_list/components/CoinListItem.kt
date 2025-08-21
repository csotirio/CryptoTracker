package com.csotirio.cryptotracker.crypto.ui.coins_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csotirio.cryptotracker.crypto.ui.model.CoinUiModel
import com.csotirio.cryptotracker.crypto.ui.model.previewCoinUiModel
import com.csotirio.cryptotracker.ui.theme.CryptoTrackerTheme
import com.csotirio.cryptotracker.util.Dimens.dimen16dp

val coinImageSize = 85.dp

@Composable
fun CoinListItem(
    uiModel: CoinUiModel,
    onCoinClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val textColor = if(isSystemInDarkTheme()){
        Color.White
    }else {
        Color.Black
    }
    Row(
        modifier = modifier
            .clickable(onClick = onCoinClicked)
            .padding(dimen16dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimen16dp)
    ){
        uiModel.iconRes?.let{ iconRes ->
            Icon(
                imageVector = ImageVector.vectorResource(iconRes),
                contentDescription = uiModel.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(coinImageSize)
            )
        }
        CoinListItemTitleAndSymbolText(
            uiModel = uiModel,
            textColor = textColor,
            modifier = Modifier.weight(1f)
        )
        CoinListItemValueAndChangeText(
            uiModel = uiModel,
            textColor = textColor
        )
    }
}

@Composable
fun CoinListItemTitleAndSymbolText(
    uiModel: CoinUiModel,
    textColor: Color,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = uiModel.symbol ?: "",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = uiModel.symbol ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = textColor
        )
    }
}

@Composable
fun CoinListItemValueAndChangeText(
    uiModel: CoinUiModel,
    textColor: Color,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "${uiModel.priceUsd?.formattedValue}",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
        uiModel.changePercent24Hr?.let{ changePercent24Hr ->
            PriceChange(
                change = changePercent24Hr,
                modifier = Modifier
            )
        }
    }
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
fun CoinListItemPreview(){
    CryptoTrackerTheme {
        CoinListItem(
            uiModel = previewCoinUiModel,
            onCoinClicked = {},
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        )
    }
}