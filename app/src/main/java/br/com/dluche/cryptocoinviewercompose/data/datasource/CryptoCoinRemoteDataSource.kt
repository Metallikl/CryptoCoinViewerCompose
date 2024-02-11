package br.com.dluche.cryptocoinviewercompose.data.datasource

import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail.CryptoDetailsDto
import br.com.dluche.cryptocoinviewercompose.common.EitherResult

interface CryptoCoinRemoteDataSource {

    suspend fun getCoins(): EitherResult<List<CryptoCoinDto>>

    suspend fun getCoinDetail(coinId: String): EitherResult<CryptoDetailsDto>
}