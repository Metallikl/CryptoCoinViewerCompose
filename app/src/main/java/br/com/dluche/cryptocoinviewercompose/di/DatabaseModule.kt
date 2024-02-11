package br.com.dluche.cryptocoinviewercompose.di

import androidx.room.Room
import br.com.dluche.cryptocoinviewercompose.common.Constants.DATABASE_NAME
import br.com.dluche.cryptocoinviewercompose.data.database.CryptoCoinDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module{
    single{
        Room.databaseBuilder(
            androidContext(),
            CryptoCoinDb::class.java,
            DATABASE_NAME
        ).build()
    }
    single{
        get<CryptoCoinDb>().coinsDao()
    }
}
