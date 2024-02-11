package br.com.dluche.cryptocoinviewercompose.data.service

object PaprikaCoinRoutes {

    const val COINS_LIST = "v1/coins"

    fun getCoinListUrl() = COINS_LIST
    fun getCoinDetailById(coinId: String) = "$COINS_LIST/$coinId"
}