package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail

import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    @SerialName("id")
    val id: String? = String.emptyString(),
    @SerialName("name")
    val name: String? = String.emptyString(),
    @SerialName("position")
    val position: String? = String.emptyString()
)