package br.com.dluche.cryptocoinviewercompose.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CryptoCoinViewerApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@CryptoCoinViewerApp)

        }
    }
}