package com.example.cointrackerapp.data.repository

import com.example.cointrackerapp.data.mapper.toCoinList
import com.example.cointrackerapp.domain.model.ListOfCoin
import com.example.cointrackerapp.domain.repository.CoinRepository
import com.example.cointrackerapp.remote.api.CoinAPI
import com.example.cointrackerapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(val api: CoinAPI) :  CoinRepository{

    override suspend fun getCoinList(): NetworkResult<Flow<ListOfCoin>> {
        return try {
            NetworkResult.Loading
            //val coinListFlow : Flow<ListOfCoin> = flow {
            val coinListFlow = flow {
                val coinList = api.getCoinList().toCoinList()
                emit(coinList)
            }
            NetworkResult.Success(coinListFlow)
        } catch (e : Exception){
            NetworkResult.Error(e.message)
        }
    }
}