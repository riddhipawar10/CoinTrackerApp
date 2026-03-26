package com.example.cointrackerapp.remote.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinGecko(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val current_price: Double?,
    val market_cap: Double?,
    val market_cap_rank: Int?,
    val price_change_percentage_24h: Double?
)