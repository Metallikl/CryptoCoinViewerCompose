package br.com.dluche.criptocoinviewer.data.datasource

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.CryptoDetailsDto

interface CryptoCoinRemoteDataSource {

    suspend fun getCoins(): EitherResult<List<CryptoCoinDto>>

    suspend fun getCoinDetail(coinId: String): EitherResult<CryptoDetailsDto>
}