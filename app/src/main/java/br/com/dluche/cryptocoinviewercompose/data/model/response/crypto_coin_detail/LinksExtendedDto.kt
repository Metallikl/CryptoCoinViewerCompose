package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail


import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksExtendedDto(
    @SerialName("stats")
    val stats: Stats = Stats(),
    @SerialName("type")
    val type: String? = String.emptyString(),
    @SerialName("url")
    val url: String? = String.emptyString()
)