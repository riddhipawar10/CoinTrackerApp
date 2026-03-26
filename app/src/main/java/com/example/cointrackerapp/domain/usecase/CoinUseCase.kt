package com.example.cointrackerapp.domain.usecase

import com.example.cointrackerapp.domain.repository.CoinRepository
import com.example.cointrackerapp.remote.model.response.CoinDataResponse
import javax.inject.Inject

class CoinUseCase @Inject constructor(private val repository: CoinRepository) {
    suspend operator fun invoke() = repository.getCoinList()
}