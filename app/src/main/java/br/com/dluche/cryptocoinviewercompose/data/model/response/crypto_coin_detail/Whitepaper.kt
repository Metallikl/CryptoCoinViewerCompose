package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail

import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Whitepaper(
    @SerializedName("link")
    val link: String? = String.emptyString(),
    @SerializedName("thumbnail")
    val thumbnail: String? = String.emptyString()
)