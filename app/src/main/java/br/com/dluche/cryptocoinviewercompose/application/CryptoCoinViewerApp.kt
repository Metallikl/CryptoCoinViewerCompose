package br.com.dluche.cryptocoinviewercompose.application

import android.app.Application
import br.com.dluche.cryptocoinviewercompose.di.cryptoCoinListModule
import br.com.dluche.cryptocoinviewercompose.di.databaseModule
import br.com.dluche.cryptocoinviewercompose.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CryptoCoinViewerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CryptoCoinViewerApp)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    cryptoCoinListModule
                )
            )

        }
    }
}