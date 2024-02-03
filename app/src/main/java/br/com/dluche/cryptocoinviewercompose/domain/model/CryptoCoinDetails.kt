package br.com.dluche.cryptocoinviewercompose.domain.model

data class CryptoCoinDetails(
    val description: String = "",
    val firstDataAt: String = "",
    val id: String = "",
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val lastDataAt: String = "",
    val logo: String = "",
    val message: String = "",
    val name: String = "",
    val openSource: Boolean = false,
    val rank: Int = 0,
    val startedAt: String = "",
    val symbol: String = "",
    val tags: List<Tag> = listOf(),
    val team: List<Team> = listOf(),
    val type: String = "",
)

data class Tag(
    val coinCounter: Int = 0,
    val icoCounter: Int = 0,
    val id: String = "",
    val name: String = ""
)

data class Team(
    val id: String = "",
    val name: String = "",
    val position: String = ""
)
