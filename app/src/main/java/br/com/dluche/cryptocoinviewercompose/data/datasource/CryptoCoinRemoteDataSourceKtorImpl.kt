package br.com.dluche.cryptocoinviewercompose.data.datasource

import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin.CryptoCoinDto
import br.com.dluche.cryptocoinviewercompose.data.model.response.crypto_coin_detail.CryptoDetailsDto
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.data.service.PaprikaCoinService

class CryptoCoinRemoteDataSourceKtorImpl(
    private val service: PaprikaCoinService
) : CryptoCoinRemoteDataSource {
    override suspend fun getCoins(): EitherResult<List<CryptoCoinDto>> {
        return try {
            EitherResult.Success(service.getCoins())
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun getCoinDetail(coinId: String): EitherResult<CryptoDetailsDto> {
        return try {
            EitherResult.Success(
                data = service.getCoinDetail(coinId = coinId)
            )
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }
}