package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("contributors")
    val contributors: Int? = 0,
    @SerialName("followers")
    val followers: Int? = 0,
    @SerialName("stars")
    val stars: Int? = 0,
    @SerialName("subscribers")
    val subscribers: Int? = 0
)