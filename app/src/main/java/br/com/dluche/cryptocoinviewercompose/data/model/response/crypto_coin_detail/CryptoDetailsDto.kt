package br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail


import br.com.dluche.cryptocoinviewercompose.extentions.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoDetailsDto(
    @SerialName("description")
    val description: String?  = String.emptyString(),
    @SerialName("development_status")
    val developmentStatus: String?  = String.emptyString(),
    @SerialName("first_data_at")
    val firstDataAt: String?  = String.emptyString(),
    @SerialName("hardware_wallet")
    val hardwareWallet: Boolean? = false,
    @SerialName("hash_algorithm")
    val hashAlgorithm: String?  = String.emptyString(),
    @SerialName("id")
    val id: String?  = String.emptyString(),
    @SerialName("is_active")
    val isActive: Boolean? = false,
    @SerialName("is_new")
    val isNew: Boolean? = false,
    @SerialName("last_data_at")
    val lastDataAt: String?  = String.emptyString(),
    @SerialName("links")
    val links: LinksDto? = LinksDto(),
    @SerialName("links_extended")
    val linksExtended: List<LinksExtendedDto>? = listOf(),
    @SerialName("logo")
    val logo: String?  = String.emptyString(),
    @SerialName("message")
    val message: String?  = String.emptyString(),
    @SerialName("name")
    val name: String?  = String.emptyString(),
    @SerialName("open_source")
    val openSource: Boolean? = false,
    @SerialName("org_structure")
    val orgStructure: String?  = String.emptyString(),
    @SerialName("proof_type")
    val proofType: String?  = String.emptyString(),
    @SerialName("rank")
    val rank: Int? = 0,
    @SerialName("started_at")
    val startedAt: String?  = String.emptyString(),
    @SerialName("symbol")
    val symbol: String?  = String.emptyString(),
    @SerialName("tags")
    val tags: List<TagDto>? = listOf(),
    @SerialName("team")
    val team: List<TeamDto>? = listOf(),
    @SerialName("type")
    val type: String?  = String.emptyString(),
    @SerialName("whitepaper")
    val whitepaper: Whitepaper? = Whitepaper()
)