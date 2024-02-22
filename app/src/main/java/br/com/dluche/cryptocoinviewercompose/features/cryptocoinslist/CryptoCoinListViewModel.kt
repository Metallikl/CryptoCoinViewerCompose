package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinsUseCase
import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CryptoCoinListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val isErrorNextPage: Boolean = false,
    val search: String? = null,
    val message: String = String.emptyString(),
    val cryptoCoinList: List<CryptoCoin> = emptyList(),
    val onTryAgain: () -> Unit = {},
    val onSearchTextChange: (String) -> Unit = {},
    val onSearchClick: () -> Unit = {}
)


class CryptoCoinListViewModel(
    private val getCoins: GetCoinsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CryptoCoinListState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        setupLambda()
        fetchCoinsList()
    }

    private fun setupLambda() {
        _uiState.update { currState ->
            currState.copy(
                onTryAgain = {
                    fetchCoinsList()
                },
                onSearchTextChange = { searchText ->
                    _uiState.update { it.copy(search = searchText) }
                },
                onSearchClick = {
                    fetchCoinsList()
                }
            )
        }
    }

    private fun fetchCoinsList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                CryptoCoinListState(
                    isLoading = true,
                    onTryAgain = it.onTryAgain
                )
            }
            getCoins(forceReset = true).collect { result ->
                when (result) {
                    is EitherResult.Error -> handleError(result.error)
                    is EitherResult.Success -> handleSuccess(result.data)
                }

            }
        }
    }

    private fun handleError(error: Throwable) {
        _uiState.update {
            it.copy(
                cryptoCoinList = emptyList(),
                isError = true,
                isLoading = false,
                message = error.message ?: String.emptyString()
            )
        }
    }

    private fun handleSuccess(data: List<CryptoCoin>) {
        _uiState.update {
            it.copy(
                cryptoCoinList = data,
                isLoading = false,
                isError = false
            )
        }
    }
}