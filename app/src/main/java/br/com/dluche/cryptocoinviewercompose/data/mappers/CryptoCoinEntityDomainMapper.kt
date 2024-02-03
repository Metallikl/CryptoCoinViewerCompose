package br.com.dluche.criptocoinviewer.data.mappers

import br.com.dluche.criptocoinviewer.common.Mapper
import br.com.dluche.criptocoinviewer.data.model.entity.CryptoCoinEntity
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinType
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin

class CryptoCoinEntityDomainMapper : Mapper<List<CryptoCoinEntity>, List<CryptoCoin>> {
    override fun mapTo(from: List<CryptoCoinEntity>): List<CryptoCoin> {
        val coins = mutableListOf<CryptoCoin>()
        if (from.isNotEmpty()) {
            from.forEach {
                coins.add(
                    CryptoCoin(
                        id = it.id,
                        isActive = it.isActive,
                        isNew = it.isNew,
                        name = it.name,
                        rank = it.rank,
                        symbol = it.symbol,
                        type = CryptoCoinType.parse(it.type.lowercase())
                    )
                )
            }
        }
        return coins
    }
}