package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

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
    val onSearchClick: () -> Unit = {},
    val onScrollEnds: () -> Unit = {}
)

fun CryptoCoinListState.copyAndKeepFuns(newState: CryptoCoinListState): CryptoCoinListState {
    return newState.copy(
        onTryAgain = onTryAgain,
        onSearchTextChange = onSearchTextChange,
        onSearchClick = onSearchClick,
        onScrollEnds = onScrollEnds
    )
}

class CryptoCoinListViewModel(
    private val dispatchers: AppCoroutinesDispatchers,
    private val getCoins: GetCoinsUseCase,
    private val getCoinsNextPageUseCase: GetCoinsNextPageUseCase
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
                    _uiState.update {
                        it.copy(search = searchText)
                    }
                },
                onSearchClick = {
                    filterCoins()
                },
                onScrollEnds = {
                    getCoinsNextPage()
                }
            )
        }
    }

    private fun fetchCoinsList() {
        viewModelScope.launch(dispatchers.io()) {
            _uiState.update { curState ->
                curState.copyAndKeepFuns(
                    CryptoCoinListState(
                        isLoading = true
                    )
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
                        _uiState.update { curState ->
                            curState.copy(
                                isLoading = false,
                                isError = false,
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
                            state.copyAndKeepFuns(
                                CryptoCoinListState(
                                    search = state.search,
                                    cryptoCoinList = newCoinList.toList(),
                                )
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