package com.example.cointrackerapp.domain.repository

import com.example.cointrackerapp.domain.model.ListOfCoin
import com.example.cointrackerapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow


interface CoinRepository {
    suspend fun getCoinList(): NetworkResult<Flow<ListOfCoin>>
}

