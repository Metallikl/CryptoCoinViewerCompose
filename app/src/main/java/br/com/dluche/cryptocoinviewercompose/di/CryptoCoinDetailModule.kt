package br.com.dluche.cryptocoinviewercompose.di

import br.com.dluche.cryptocoinviewercompose.features.cryptocoindetail.CryptoCoinDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinDetailsUseCase
import br.com.dluche.cryptocoinviewercompose.domain.usecase.GetCoinDetailsUseCaseImpl

val cryptoCoinDetailModule = module{
    viewModelOf(::CryptoCoinDetailViewModel)
    factoryOf(::GetCoinDetailsUseCaseImpl){ bind<GetCoinDetailsUseCase>()}
}