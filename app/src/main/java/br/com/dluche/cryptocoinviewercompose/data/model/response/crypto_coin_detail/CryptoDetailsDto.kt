package br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail


import br.com.dluche.criptocoinviewer.extensions.emptyString
import com.google.gson.annotations.SerializedName

data class CryptoDetailsDto(
    @SerializedName("description")
    val description: String?  = String.emptyString(),
    @SerializedName("development_status")
    val developmentStatus: String?  = String.emptyString(),
    @SerializedName("first_data_at")
    val firstDataAt: String?  = String.emptyString(),
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean? = false,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String?  = String.emptyString(),
    @SerializedName("id")
    val id: String?  = String.emptyString(),
    @SerializedName("is_active")
    val isActive: Boolean? = false,
    @SerializedName("is_new")
    val isNew: Boolean? = false,
    @SerializedName("last_data_at")
    val lastDataAt: String?  = String.emptyString(),
    @SerializedName("links")
    val links: LinksDto? = LinksDto(),
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtendedDto>? = listOf(),
    @SerializedName("logo")
    val logo: String?  = String.emptyString(),
    @SerializedName("message")
    val message: String?  = String.emptyString(),
    @SerializedName("name")
    val name: String?  = String.emptyString(),
    @SerializedName("open_source")
    val openSource: Boolean? = false,
    @SerializedName("org_structure")
    val orgStructure: String?  = String.emptyString(),
    @SerializedName("proof_type")
    val proofType: String?  = String.emptyString(),
    @SerializedName("rank")
    val rank: Int? = 0,
    @SerializedName("started_at")
    val startedAt: String?  = String.emptyString(),
    @SerializedName("symbol")
    val symbol: String?  = String.emptyString(),
    @SerializedName("tags")
    val tags: List<TagDto>? = listOf(),
    @SerializedName("team")
    val team: List<TeamDto>? = listOf(),
    @SerializedName("type")
    val type: String?  = String.emptyString(),
    @SerializedName("whitepaper")
    val whitepaper: Whitepaper? = Whitepaper()
)