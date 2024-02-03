package br.com.dluche.cryptocoinviewercompose.domain.model

data class CoinPaginationData(
    val search: String? = null,
    val offset: Int = 0,
    val limit: Int = 20
)
