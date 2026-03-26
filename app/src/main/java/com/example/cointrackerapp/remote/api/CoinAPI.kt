package com.example.cointrackerapp.remote.api


import com.example.cointrackerapp.remote.model.response.CoinGecko
import retrofit2.http.GET

interface CoinAPI {
    @GET("coins/markets?vs_currency=usd")
    suspend fun getCoinList(): List<CoinGecko>
}