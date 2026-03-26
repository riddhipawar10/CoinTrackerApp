package com.example.cointrackerapp.data.mapper

import com.example.cointrackerapp.domain.model.Coin
import com.example.cointrackerapp.domain.model.ListOfCoin
import com.example.cointrackerapp.remote.model.response.CoinGecko

fun List<CoinGecko>.toCoinList(): ListOfCoin {
    return ListOfCoin(
        data = this.mapNotNull {

            val id = it.id ?: return@mapNotNull null
            val name = it.name ?: return@mapNotNull null
            val symbol = it.symbol ?: return@mapNotNull null

            Coin(
                id = id,
                rank = it.market_cap_rank ?: 0,
                name = name,
                symbol = symbol,
                marketCapUsd = it.market_cap ?: 0.0,
                priceUsd = it.current_price ?: 0.0,
                changePercent24Hr = it.price_change_percentage_24h ?: 0.0
            )
        },
        timestamp = System.currentTimeMillis()
    )
}