package br.com.dluche.cryptocoinviewercompose.domain.usecase

import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CoinPaginationData
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoin
import br.com.dluche.cryptocoinviewercompose.domain.repository.CryptoCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinsUseCaseImpl(
    private val repository: CryptoCoinRepository
) : GetCoinsUseCase {
    override suspend fun invoke(
        forceReset: Boolean,
        paginationData: CoinPaginationData
    ): Flow<EitherResult<List<CryptoCoin>>> = flow {
        emit(repository.getCoins(forceReset = forceReset, paginationData = paginationData))
    }
}