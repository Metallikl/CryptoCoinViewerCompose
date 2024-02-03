package br.com.dluche.criptocoinviewer.data.mappers

import br.com.dluche.criptocoinviewer.common.Mapper
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.CryptoDetailsDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.TagDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.TeamDto
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails
import br.com.dluche.criptocoinviewer.domain.model.Tag
import br.com.dluche.criptocoinviewer.domain.model.Team
import br.com.dluche.criptocoinviewer.extensions.orDefault
import br.com.dluche.criptocoinviewer.extensions.orFalse

class CryptoCoinDetailsDtoDomainMapper : Mapper<CryptoDetailsDto, CryptoCoinDetails> {
    override fun mapTo(from: CryptoDetailsDto): CryptoCoinDetails {
        return CryptoCoinDetails(
            description = from.description.orEmpty(),
            firstDataAt = from.firstDataAt.orEmpty(),
            id = from.id.orEmpty(),
            isActive = from.isActive.orFalse(),
            isNew = from.isNew.orFalse(),
            lastDataAt = from.lastDataAt.orEmpty(),
            logo = from.logo.orEmpty(),
            message = from.message.orEmpty(),
            name = from.name.orEmpty(),
            openSource = from.openSource.orFalse(),
            rank = from.rank?:0,
            startedAt = from.startedAt.orEmpty(),
            symbol = from.symbol.orEmpty(),
            tags = mapTags(from.tags.orEmpty()),
            team = mapTeam(from.team.orEmpty()),
            type = from.type.orEmpty()
        )
    }

    private fun mapTags(tags: List<TagDto>): List<Tag> {
        return tags.map {
            Tag(
                coinCounter = it.coinCounter.orDefault(),
                icoCounter = it.icoCounter.orDefault(),
                id = it.id.orEmpty(),
                name = it.name.orEmpty()
            )
        }
    }

    private fun mapTeam(team: List<TeamDto>): List<Team> {
        return team.map {
            Team(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                position = it.position.orEmpty()
            )
        }
    }
}