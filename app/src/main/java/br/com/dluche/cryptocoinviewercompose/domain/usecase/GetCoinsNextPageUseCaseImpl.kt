package br.com.dluche.cryptocoinviewercompose.domain.usecase

import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CoinPaginationData
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.repository.CryptoCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinsNextPageUseCaseImpl(
    private val repository: CryptoCoinRepository
) : GetCoinsNextPageUseCase {
    override suspend fun invoke(
        paginationData: CoinPaginationData
    ): Flow<EitherResult<List<CryptoCoin>>> = flow {
        emit(
            repository.getCoinsNextPage(
                paginationData
            )
        )
    }
}