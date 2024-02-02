package br.com.dluche.cryptocoinviewercompose.domain.model

import br.com.dluche.cryptocoinviewercompose.extentions.emptyString

data class CryptoCoin(
    val id: String = String.emptyString(),
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val name: String = String.emptyString(),
    val rank: Int = 0,
    val symbol: String = String.emptyString(),
    val type: CryptoCoinType = CryptoCoinType.COIN
)
