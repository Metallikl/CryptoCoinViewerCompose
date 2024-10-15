package br.com.dluche.cryptocoinviewercompose.di

import br.com.dluche.cryptocoinviewercompose.features.cryptocoindetail.CryptoCoinDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val cryptoCoinDetailModule = module{
    viewModelOf(::CryptoCoinDetailViewModel)
}