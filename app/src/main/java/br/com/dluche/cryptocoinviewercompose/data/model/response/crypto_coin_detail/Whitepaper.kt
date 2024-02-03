package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import br.com.dluche.criptocoinviewer.extensions.emptyString
import com.google.gson.annotations.SerializedName

data class Whitepaper(
    @SerializedName("link")
    val link: String? = String.emptyString(),
    @SerializedName("thumbnail")
    val thumbnail: String? = String.emptyString()
)