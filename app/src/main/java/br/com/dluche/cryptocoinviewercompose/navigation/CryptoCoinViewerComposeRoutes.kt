package br.com.dluche.cryptocoinviewercompose.navigation

import kotlinx.serialization.Serializable

sealed interface CryptoCoinViewerComposeRoutes {
    @Serializable
    data object CryptoCoinList : CryptoCoinViewerComposeRoutes

    @Serializable
    data class CryptoCoinDetail(val id: String) : CryptoCoinViewerComposeRoutes
}