package br.com.dluche.cryptocoinviewercompose.client

import br.com.dluche.cryptocoinviewercompose.common.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ClientProvider {

    fun provideClient() = HttpClient(Android) {
        install(Logging){
            level = LogLevel.ALL
        }
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url(Constants.BASE_URL)
        }
    }
}