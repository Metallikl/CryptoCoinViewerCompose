package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail


import br.com.dluche.cryptocoinviewercompose.extentions.emptyString

import com.google.gson.annotations.SerializedName

data class LinksExtendedDto(
    @SerializedName("stats")
    val stats: Stats = Stats(),
    @SerializedName("type")
    val type: String? = String.emptyString(),
    @SerializedName("url")
    val url: String? = String.emptyString()
)