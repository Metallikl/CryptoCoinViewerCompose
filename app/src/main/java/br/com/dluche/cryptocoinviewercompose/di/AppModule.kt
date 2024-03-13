package br.com.dluche.cryptocoinviewercompose.di

import br.com.dluche.cryptocoinviewercompose.common.AppCoroutinesDispatchers
import br.com.dluche.cryptocoinviewercompose.common.AppCoroutinesDispatchersImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module{
    singleOf(::AppCoroutinesDispatchersImpl) { bind<AppCoroutinesDispatchers>()}
}