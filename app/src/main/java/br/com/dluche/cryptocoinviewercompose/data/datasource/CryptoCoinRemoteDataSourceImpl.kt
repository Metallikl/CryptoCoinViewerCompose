package br.com.dluche.criptocoinviewer.data.datasource

import br.com.dluche.cryptocoinviewercompose.data.api.PaprikaCoinApi
import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.CryptoDetailsDto
import javax.inject.Inject

class CryptoCoinRemoteDataSourceImpl @Inject constructor(
    private val api: PaprikaCoinApi
) : CryptoCoinRemoteDataSource {
    override suspend fun getCoins(): EitherResult<List<CryptoCoinDto>> {
        return try {
            val coinsResponse = api.getCoins()
            if (coinsResponse.isSuccessful) {
                EitherResult.Success(data = coinsResponse.body().orEmpty())
            } else {
                throw Exception(coinsResponse.message().toString())
            }
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }

    }

    override suspend fun getCoinDetail(coinId: String): EitherResult<CryptoDetailsDto> {
        return try {
            val coinsResponse = api.getCoinDetail(coinId = coinId)
            if (coinsResponse.isSuccessful) {
                EitherResult.Success(data = coinsResponse.body() ?: CryptoDetailsDto())
            } else {
                throw Exception(coinsResponse.message().toString())
            }
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }
}