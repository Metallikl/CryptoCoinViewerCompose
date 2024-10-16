package br.com.dluche.cryptocoinviewercompose.data.service

import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail.CryptoDetailsDto
import br.com.dluche.cryptocoinviewercompose.data.service.PaprikaCoinRoutes.COINS_LIST
import br.com.dluche.cryptocoinviewercompose.data.service.PaprikaCoinRoutes.getCoinDetailById
import br.com.dluche.cryptocoinviewercompose.data.service.PaprikaCoinRoutes.getCoinListUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PaprikaCoinServiceImpl(
    private val httpClient: HttpClient
) : PaprikaCoinService {
    override suspend fun getCoins(): List<CryptoCoinDto> {
       return httpClient.get(COINS_LIST).body()
    }

    override suspend fun getCoinDetail(coinId: String): CryptoDetailsDto {
        return httpClient.get(getCoinDetailById(coinId)).body<CryptoDetailsDto>()
    }
}