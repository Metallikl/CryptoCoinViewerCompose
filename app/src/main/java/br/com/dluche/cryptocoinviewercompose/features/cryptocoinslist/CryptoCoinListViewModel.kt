package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dluche.cryptocoinviewercompose.common.AppCoroutinesDispatchers
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CoinPaginationData
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinsNextPageUseCase
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinsUseCase
import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoCoinListViewModel(
    private val dispatchers: AppCoroutinesDispatchers,
    private val getCoins: GetCoinsUseCase,
    private val getCoinsNextPageUseCase: GetCoinsNextPageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CryptoCoinListState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        fetchCoinsList()
    }

    fun onEvent(event: CryptoCoinListEvent) {
        when (event) {
            is CryptoCoinListEvent.SearchTextChange -> {
                _uiState.update {
                    it.copy(search = event.searchText)
                }
            }

            CryptoCoinListEvent.SearchClick -> {
                filterCoins()
            }

            CryptoCoinListEvent.LoadNextPage -> {
                getCoinsNextPage()
            }

            CryptoCoinListEvent.TryAgain -> {
                fetchCoinsList()
            }
        }
    }

    private fun fetchCoinsList() {
        viewModelScope.launch(dispatchers.io()) {
            _uiState.update { curState ->
                curState.copy(
                    isLoading = true,
                    isError = false,
                    isLoadingNextPage = false,
                    isErrorNextPage = false,
                    message = String.emptyString(),
                    cryptoCoinList = emptyList()
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

    private fun filterCoins() {
        viewModelScope.launch(dispatchers.io()) {
            _uiState.update { curState ->
                curState.copy(
                    isLoading = true
                )
            }
            //
            getCoinsNextPageUseCase(
                paginationData = CoinPaginationData(
                    search = _uiState.value.search
                )
            ).collect { result ->
                when (result) {
                    is EitherResult.Error -> {
                        Log.d("CryptoCoinListVm", "NextPageError")
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                isLoadingNextPage = false,
                                isErrorNextPage = false,
                                cryptoCoinList = emptyList(),
                                message = result.error.message.orEmpty()
                            )
                        }
                    }

                    is EitherResult.Success -> {
                        Log.d("CryptoCoinListVm", "NextPageSuccess")
                        _uiState.update { curState ->
                            curState.copy(
                                isLoading = false,
                                isError = false,
                                isLoadingNextPage = false,
                                isErrorNextPage = false,
                                message = String.emptyString(),
                                cryptoCoinList = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCoinsNextPage() {
        viewModelScope.launch(dispatchers.io()) {
            _uiState.update { curState ->
                curState.copy(
                    isLoadingNextPage = true
                )
            }
            //
            getCoinsNextPageUseCase(
                buildPaginationData()
            ).collect { result ->
                when (result) {
                    is EitherResult.Error -> {
                        Log.d("CryptoCoinListVm", "NextPageError\n ${result.error.message.orEmpty()}")
                        _uiState.update { curState ->
                            curState.copy(
                                isLoading = false,
                                isLoadingNextPage = false,
                                isErrorNextPage = true,
                                message = result.error.message.orEmpty()
                            )
                        }
                    }

                    is EitherResult.Success -> {
                        _uiState.update { state ->
                            val newCoinList = state
                                .cryptoCoinList
                                .toMutableList()
                                .also {
                                    it.addAll(result.data)
                                }
                            //
                            Log.d("CryptoCoinListVm", "NextPageSuccess\n ${result.data}")
                            state.copy(
                                isLoading = false,
                                isError = false,
                                isLoadingNextPage = false,
                                isErrorNextPage = false,
                                search = state.search,
                                cryptoCoinList = newCoinList.toList(),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun buildPaginationData() = CoinPaginationData(
        search = _uiState.value.search,
        offset = _uiState.value.cryptoCoinList.size
    )


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