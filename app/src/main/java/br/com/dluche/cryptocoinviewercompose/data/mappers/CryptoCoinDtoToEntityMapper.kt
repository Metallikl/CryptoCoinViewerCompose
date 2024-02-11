package br.com.dluche.cryptocoinviewercompose.data.mappers

import br.com.dluche.cryptocoinviewercompose.data.model.entity.CryptoCoinEntity
import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.cryptocoinviewercompose.common.Mapper

class CryptoCoinDtoToEntityMapper : Mapper<List<CryptoCoinDto>, List<CryptoCoinEntity>> {
    override fun mapTo(from: List<CryptoCoinDto>): List<CryptoCoinEntity> {
        val coins = mutableListOf<CryptoCoinEntity>()
        if (from.isNotEmpty()) {
            from.forEach {
                coins.add(
                    CryptoCoinEntity(
                        id = it.id,
                        isActive = it.isActive,
                        isNew = it.isNew,
                        name = it.name,
                        rank = it.rank,
                        symbol = it.symbol,
                        type = it.type
                    )
                )
            }
        }
        return coins
    }
}