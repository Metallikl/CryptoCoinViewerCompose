package br.com.dluche.cryptocoinviewercompose.domain.usecase

import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CoinPaginationData
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin

import kotlinx.coroutines.flow.Flow

interface GetCoinsUseCase {
    suspend operator fun invoke(
        forceReset: Boolean = false,
        paginationData: CoinPaginationData = CoinPaginationData()
    ): Flow<EitherResult<List<CryptoCoin>>>
}