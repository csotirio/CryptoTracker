package com.csotirio.cryptotracker.crypto.ui.coins_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csotirio.cryptotracker.common.model.DisplayableNumberUiModel
import com.csotirio.cryptotracker.ui.theme.CryptoTrackerTheme
import com.csotirio.cryptotracker.ui.theme.greenBackground
import com.csotirio.cryptotracker.util.Dimens.dimen4dp

@Composable
fun PriceChange(
    change: DisplayableNumberUiModel,
    modifier: Modifier
) {
    val color = if (change.value < 0.0) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        Color.Green
    }
    val backgroundColor = if (change.value < 0.0) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        greenBackground
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(horizontal = dimen4dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (change.value < 0.0) {
                Icons.Default.ArrowDropDown
            } else {
                Icons.Default.ArrowDropUp
            },
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = color
        )
        Text(
            text = change.formattedValue,
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@PreviewLightDark
@Composable
fun PriceChangePreview() {
    CryptoTrackerTheme {
        PriceChange(
            change = DisplayableNumberUiModel(
                value = 0.1,
                formattedValue = "-0.1%"
            ),
            modifier = Modifier
        )
    }
}