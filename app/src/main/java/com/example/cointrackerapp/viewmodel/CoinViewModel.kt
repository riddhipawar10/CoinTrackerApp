package com.example.cointrackerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointrackerapp.domain.model.ListOfCoin
import com.example.cointrackerapp.domain.usecase.CoinUseCase
import com.example.cointrackerapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(private val coinUseCase: CoinUseCase): ViewModel() {

    private val _coinListState = MutableStateFlow(ListOfCoin(emptyList(), 0))
    val coinListState : StateFlow<ListOfCoin> =  _coinListState

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState

    fun callGetCoinList() = viewModelScope.launch{
        _loadingState.value = true
        when(val result = coinUseCase.invoke()) {
            is NetworkResult.Success -> {
                _loadingState.value = false
                result.data?.collect {
                    _coinListState.value = it
                }
            }
            is NetworkResult.Error -> {
                _loadingState.value = false
            }
            else -> {
                _loadingState.value =  false
            }
        }
    }
}