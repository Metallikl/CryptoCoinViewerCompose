package br.com.dluche.cryptocoinviewercompose.domain.model

import br.com.dluche.cryptocoinviewercompose.extentions.emptyString

data class CryptoCoinDetails(
    val description: String = String.emptyString(),
    val firstDataAt: String = String.emptyString(),
    val id: String = String.emptyString(),
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val lastDataAt: String = String.emptyString(),
    val logo: String = String.emptyString(),
    val message: String = String.emptyString(),
    val name: String = String.emptyString(),
    val openSource: Boolean = false,
    val rank: Int = 0,
    val startedAt: String = String.emptyString(),
    val symbol: String = String.emptyString(),
    val tags: List<Tag> = listOf(),
    val team: List<Team> = listOf(),
    val type: String = String.emptyString(),
)

data class Tag(
    val coinCounter: Int = 0,
    val icoCounter: Int = 0,
    val id: String = String.emptyString(),
    val name: String = String.emptyString()
)

data class Team(
    val id: String = String.emptyString(),
    val name: String = String.emptyString(),
    val position: String = String.emptyString()
)
