package br.com.dluche.cryptocoinviewercompose.domain.usecase

import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CoinPaginationData
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import kotlinx.coroutines.flow.Flow

interface GetCoinsNextPageUseCase {
    suspend operator fun invoke(paginationData: CoinPaginationData): Flow<EitherResult<List<CryptoCoin>>>
}