package br.com.dluche.cryptocoinviewercompose.di

import br.com.dluche.cryptocoinviewercompose.client.ClientProvider.provideClient
import br.com.dluche.cryptocoinviewercompose.data.datasource.CryptoCoinLocalDataSource
import br.com.dluche.cryptocoinviewercompose.data.datasource.CryptoCoinLocalDataSourceImpl
import br.com.dluche.cryptocoinviewercompose.data.datasource.CryptoCoinRemoteDataSource
import br.com.dluche.cryptocoinviewercompose.data.datasource.CryptoCoinRemoteDataSourceKtorImpl
import br.com.dluche.cryptocoinviewercompose.data.mappers.CryptoCoinDetailsDtoDomainMapper
import br.com.dluche.cryptocoinviewercompose.data.mappers.CryptoCoinDtoToEntityMapper
import br.com.dluche.cryptocoinviewercompose.data.mappers.CryptoCoinEntityDomainMapper
import br.com.dluche.cryptocoinviewercompose.data.repository.CryptoCoinRepositoryImpl
import br.com.dluche.cryptocoinviewercompose.data.service.PaprikaCoinService
import br.com.dluche.cryptocoinviewercompose.data.service.PaprikaCoinServiceImpl
import br.com.dluche.cryptocoinviewercompose.domain.repository.CryptoCoinRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::provideClient)
    singleOf(::PaprikaCoinServiceImpl) {bind<PaprikaCoinService>()}
    factoryOf(::CryptoCoinRepositoryImpl) { bind<CryptoCoinRepository>() }
    factoryOf(::CryptoCoinRemoteDataSourceKtorImpl) { bind<CryptoCoinRemoteDataSource>() }
    factoryOf(::CryptoCoinLocalDataSourceImpl) { bind<CryptoCoinLocalDataSource>() }
    factoryOf(::CryptoCoinDetailsDtoDomainMapper)
    factoryOf(::CryptoCoinDtoToEntityMapper)
    factoryOf(::CryptoCoinEntityDomainMapper)
}