package br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist

sealed interface CryptoCoinListEvent {
    data class  SearchTextChange(val searchText: String) : CryptoCoinListEvent
    data object SearchClick : CryptoCoinListEvent
    data object TryAgain : CryptoCoinListEvent
    data object LoadNextPage : CryptoCoinListEvent
}