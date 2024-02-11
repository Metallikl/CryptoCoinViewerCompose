package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CryptoCoinListState(
    val cryptoCoinList: List<CryptoCoin> = emptyList()
)


class CryptoCoinListViewModel(
    private val getCoins: GetCoinsUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(CryptoCoinListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCoins(forceReset = true).collect{ result->
                when(result){
                    is EitherResult.Error -> handleError()
                    is EitherResult.Success -> handleSuccess(result.data)
                }

            }
        }
    }

    private fun handleError() {
        _uiState.update {
            it.copy(cryptoCoinList = emptyList())
        }
    }

    private fun handleSuccess(data: List<CryptoCoin>) {
        _uiState.update {
            it.copy(cryptoCoinList = data)
        }
    }

}