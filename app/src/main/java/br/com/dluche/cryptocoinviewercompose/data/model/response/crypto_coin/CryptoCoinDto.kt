package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin


import br.com.dluche.criptocoinviewer.extensions.emptyString
import com.google.gson.annotations.SerializedName

data class CryptoCoinDto(
    @SerializedName("id")
    val id: String = String.emptyString(),
    @SerializedName("is_active")
    val isActive: Boolean = false,
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("name")
    val name: String = String.emptyString(),
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("symbol")
    val symbol: String = String.emptyString(),
    @SerializedName("type")
    val type: String = String.emptyString()
)