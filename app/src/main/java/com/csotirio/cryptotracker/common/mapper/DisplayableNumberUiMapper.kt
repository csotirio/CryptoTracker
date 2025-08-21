package com.csotirio.cryptotracker.common.mapper

import com.csotirio.cryptotracker.common.model.DisplayableNumberUiModel
import java.text.NumberFormat
import java.util.Locale

fun Double.toDisplayableNumberUiModel(): DisplayableNumberUiModel {
    val numberFormater = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayableNumberUiModel(
        value = this,
        formattedValue = numberFormater.format(this)
    )
}

