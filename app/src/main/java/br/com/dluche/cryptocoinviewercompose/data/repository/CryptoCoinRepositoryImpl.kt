package br.com.dluche.criptocoinviewer.data.repository

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.data.datasource.CryptoCoinLocalDataSource
import br.com.dluche.criptocoinviewer.data.datasource.CryptoCoinRemoteDataSource
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinDtoDomainMapper
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinDetailsDtoDomainMapper
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinDtoToEntityMapper
import br.com.dluche.criptocoinviewer.data.mappers.CryptoCoinEntityDomainMapper
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails
import br.com.dluche.criptocoinviewer.domain.repository.CryptoCoinRepository
import javax.inject.Inject


class CryptoCoinRepositoryImpl @Inject constructor(
    private val remoteDataSource: CryptoCoinRemoteDataSource,
    private val localDataSource: CryptoCoinLocalDataSource,
    private val cryptoCoinMapper: CryptoCoinDtoDomainMapper,
    private val cryptoCoinDetailsMapper: CryptoCoinDetailsDtoDomainMapper,
    private val cryptoCoinDtoToEntityMapper: CryptoCoinDtoToEntityMapper,
    private val cryptoCoinEntityToDomainMapper: CryptoCoinEntityDomainMapper,
) : CryptoCoinRepository {

    override suspend fun getCoins(
        forceReset: Boolean,
        paginationData: CoinPaginationData
    ): EitherResult<List<CryptoCoin>> {
        return if (forceReset) {
            localDataSource.deleteCoins()
            getRemoteAndCache(paginationData)
        } else {
            getLocalCoins(paginationData)
        }
    }

    private suspend fun getRemoteAndCache(
        paginationData: CoinPaginationData
    ): EitherResult<List<CryptoCoin>> {
        return when (val coins = remoteDataSource.getCoins()) {
            is EitherResult.Error -> {
                coins
            }
            is EitherResult.Success -> {
                try {
                    localDataSource.insertCoins(
                        cryptoCoinDtoToEntityMapper.mapTo(
                            coins.data
                        )
                    )
                    //
                    getLocalCoins(paginationData)
                } catch (e: Exception) {
                    EitherResult.Error(e)
                }
            }
        }
    }

    private suspend fun getLocalCoins(paginationData: CoinPaginationData): EitherResult<List<CryptoCoin>> {
        return when (val localCoins =
            localDataSource.getCoins(paginationData.offset, paginationData.limit)) {
            is EitherResult.Error -> {
                localCoins
            }
            is EitherResult.Success -> {
                EitherResult.Success(
                    cryptoCoinEntityToDomainMapper.mapTo(
                        localCoins.data
                    )
                )
            }
        }
    }

    override suspend fun getCoinDetails(coinId: String): EitherResult<CryptoCoinDetails> {
        return when (val coinsDetails = remoteDataSource.getCoinDetail(coinId)) {
            is EitherResult.Error -> {
                coinsDetails
            }
            is EitherResult.Success -> {
                EitherResult.Success(
                    cryptoCoinDetailsMapper.mapTo(
                        coinsDetails.data
                    )
                )
            }
        }
    }

    override suspend fun getCoinsNextPage(paginationData: CoinPaginationData): EitherResult<List<CryptoCoin>> {
        val coinsNextPage = localDataSource.getCoinsNextPage(
            paginationData.search,
            paginationData.offset,
            paginationData.limit
        )

        return when (coinsNextPage) {
            is EitherResult.Error -> {
                coinsNextPage
            }
            is EitherResult.Success -> {
                EitherResult.Success(
                    cryptoCoinEntityToDomainMapper.mapTo(
                        coinsNextPage.data
                    )
                )
            }
        }
    }
}