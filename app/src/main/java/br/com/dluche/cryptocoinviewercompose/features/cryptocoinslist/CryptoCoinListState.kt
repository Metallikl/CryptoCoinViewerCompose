package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.extentions.emptyString

data class CryptoCoinListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val isErrorNextPage: Boolean = false,
    val search: String? = null,
    val message: String = String.emptyString(),
    val cryptoCoinList: List<CryptoCoin> = emptyList()
)