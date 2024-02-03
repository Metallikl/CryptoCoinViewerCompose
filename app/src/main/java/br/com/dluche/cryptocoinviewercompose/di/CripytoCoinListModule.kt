package br.com.dluche.cryptocoinviewercompose.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val cripytoCoinListModule = module{

    viewModelOf(::CriptoCoinListViewmodel)

}