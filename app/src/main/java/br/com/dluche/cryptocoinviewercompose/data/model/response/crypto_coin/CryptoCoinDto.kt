package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin


import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoCoinDto(
    @SerialName("id")
    val id: String = String.emptyString(),
    @SerialName("is_active")
    val isActive: Boolean = false,
    @SerialName("is_new")
    val isNew: Boolean = false,
    @SerialName("name")
    val name: String = String.emptyString(),
    @SerialName("rank")
    val rank: Int = 0,
    @SerialName("symbol")
    val symbol: String = String.emptyString(),
    @SerialName("type")
    val type: String = String.emptyString()
)