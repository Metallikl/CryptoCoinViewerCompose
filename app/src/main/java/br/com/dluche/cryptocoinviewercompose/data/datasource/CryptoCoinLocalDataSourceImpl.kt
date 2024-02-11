package br.com.dluche.cryptocoinviewercompose.data.datasource

import br.com.dluche.cryptocoinviewercompose.data.model.entity.CryptoCoinEntity
import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.data.database.dao.CryptoCoinDao

class CryptoCoinLocalDataSourceImpl(
    private val coinDao: CryptoCoinDao
) : CryptoCoinLocalDataSource {

    override suspend fun insertCoins(coins: List<CryptoCoinEntity>): EitherResult<Any> {
        return try {
            coinDao.insertAll(coins)
            EitherResult.Success(Any())
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun getCoins(offset: Int, limit: Int): EitherResult<List<CryptoCoinEntity>> {
        return try {
            EitherResult.Success(coinDao.getCoins(offset = offset, limit = limit))
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun deleteCoins(): EitherResult<Any> {
        return try {
            coinDao.deleteAll()
            EitherResult.Success(Any())
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun getCoinsNextPage(
        search: String?,
        offset: Int,
        limit: Int
    ): EitherResult<List<CryptoCoinEntity>> {
        return try {
            EitherResult.Success(
                coinDao.getCoinsNextPage(
                    search = search,
                    offset = offset,
                    limit = limit
                )
            )
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }

    }
}