package br.com.dluche.cryptocoinviewercompose.domain.usecase

import br.com.dluche.cryptocoinviewercompose.common.EitherResult
import br.com.dluche.cryptocoinviewercompose.domain.model.CryptoCoinDetails
import kotlinx.coroutines.flow.Flow

interface GetCoinDetailsUseCase {
    suspend operator fun invoke(coinId: String): Flow<EitherResult<CryptoCoinDetails>>
}