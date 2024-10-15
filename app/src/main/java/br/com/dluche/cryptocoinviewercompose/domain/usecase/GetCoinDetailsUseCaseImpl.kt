package br.com.dluche.cryptocoinviewercompose.domain.usecase

import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinDetails
import br.com.dluche.cryptocoinviewercompose.domain.repository.CryptoCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinDetailsUseCaseImpl(
    private val repository: CryptoCoinRepository
) : GetCoinDetailsUseCase {
    override suspend operator fun invoke(coinId: String): Flow<EitherResult<CryptoCoinDetails>> =
        flow {
            emit(repository.getCoinDetails(coinId))
        }
}