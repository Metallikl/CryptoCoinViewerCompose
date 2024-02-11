package br.com.dluche.cryptocoinviewercompose.data.datasource

import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail.CryptoDetailsDto
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.data.api.PaprikaCoinApi

class CryptoCoinRemoteDataSourceRetrofitImpl(
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