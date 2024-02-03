package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import br.com.dluche.criptocoinviewer.extensions.emptyString
import com.google.gson.annotations.SerializedName

data class TeamDto(
    @SerializedName("id")
    val id: String? = String.emptyString(),
    @SerializedName("name")
    val name: String? = String.emptyString(),
    @SerializedName("position")
    val position: String? = String.emptyString()
)