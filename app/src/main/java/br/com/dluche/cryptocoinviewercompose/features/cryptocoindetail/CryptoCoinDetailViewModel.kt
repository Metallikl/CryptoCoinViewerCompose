package br.com.dluche.cryptocoinviewercompose.features.cryptocoindetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dluche.cryptocoinviewercompose.common.AppCoroutinesDispatchers
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinDetails
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinDetailsUseCase
import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoCoinDetailViewModel(
    private val dispatchers: AppCoroutinesDispatchers,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(CoinDetailsState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>("coinId")?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId: String) {
        viewModelScope.launch(dispatchers.io()) {
            _uiState.update { curState ->
                curState.copy(
                    isLoading = true,
                    isError = false,
                    message = String.emptyString()
                )
            }

            getCoinDetailsUseCase(coinId)
                .collect { result->
                    when(result) {
                        is EitherResult.Error -> {
                            handlerError(result.error.message.orEmpty())
                        }
                        is EitherResult.Success -> {
                            handleSuccess(result.data)
                        }
                    }

                }
        }
    }

    private fun handleSuccess(data: CryptoCoinDetails) {
        _uiState.update { curState->
            curState.copy(
                isLoading = false,
                isError = false,
                message = String.emptyString(),
                coinDetail = data
            )
        }
    }

    private fun handlerError(errorMessage: String) {
        _uiState.update { curState ->
            curState.copy(
                isLoading = false,
                isError = true,
                message = errorMessage
            )
        }
    }
}