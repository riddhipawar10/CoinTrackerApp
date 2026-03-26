package com.example.cointrackerapp.presentation.model

import android.graphics.drawable.Icon
import android.icu.number.NumberFormatter
import android.icu.text.FormattedValue
import android.text.InputFilter
import androidx.annotation.DrawableRes
import com.example.cointrackerapp.remote.model.response.CoinDataResponse
import java.text.NumberFormat
import java.util.Locale
import com.example.cointrackerapp.domain.model.Coin
import com.example.cointrackerapp.presentation.utils.getDrawableForCoin

data class CoinUI(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayNumber,
    val priceUsd: DisplayNumber,
    val changePercent24Hr: DisplayNumber,
    @DrawableRes val icon: Int

)

data class DisplayNumber(
    val value: Double,
    val formattedValue: String
)

fun Coin.toCoinUI(): CoinUI {
    return CoinUI(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayNumberFormat(),
        priceUsd = priceUsd.toDisplayNumberFormat(),
        changePercent24Hr = changePercent24Hr.toDisplayNumberFormat(),
        icon = getDrawableForCoin(symbol)
    )
}


fun Double.toDisplayNumberFormat(): DisplayNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayNumber(this, formatter.format(this))

}
