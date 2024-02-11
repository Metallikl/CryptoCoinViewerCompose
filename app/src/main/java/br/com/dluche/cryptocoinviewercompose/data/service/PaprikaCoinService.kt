package br.com.dluche.cryptocoinviewercompose.data.service

import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail.CryptoDetailsDto

interface PaprikaCoinService {

    suspend fun getCoins(): List<CryptoCoinDto>

    suspend fun getCoinDetail(coinId: String): CryptoDetailsDto
}