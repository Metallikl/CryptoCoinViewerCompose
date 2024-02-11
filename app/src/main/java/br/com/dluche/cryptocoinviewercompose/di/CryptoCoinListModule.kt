package br.com.dluche.cryptocoinviewercompose.di

import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinsUseCase
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinsUseCaseImpl
import br.com.dluche.cryptocoinviewercompose.features.cryptocoinslist.CryptoCoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cryptoCoinListModule = module {
    viewModelOf(::CryptoCoinListViewModel)
    factoryOf(::GetCoinsUseCaseImpl) { bind<GetCoinsUseCase>() }
}

