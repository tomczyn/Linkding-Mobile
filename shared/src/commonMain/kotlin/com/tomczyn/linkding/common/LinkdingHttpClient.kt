package com.tomczyn.linkding.common

import com.tomczyn.linkding.LinkdingSettings
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.getString
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

expect fun provideEngine(): HttpClientEngineFactory<*>

@Suppress("FunctionName")
@OptIn(ExperimentalSerializationApi::class)
fun LinkdingHttpClient(
    settings: Settings
): HttpClient = HttpClient(provideEngine()) {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                co.touchlab.kermit.Logger.d(tag = "HttpClient") { message }
            }
        }
        level = LogLevel.ALL
    }
    install(ContentNegotiation) {
        json(
            json = Json {
                encodeDefaults = true
                isLenient = true
                allowSpecialFloatingPointValues = true
                allowStructuredMapKeys = true
                prettyPrint = true
                useArrayPolymorphism = false
                ignoreUnknownKeys = true
                explicitNulls = false
            },
            contentType = ContentType.Application.Json
        )
    }
    defaultRequest {
        contentType(ContentType.Application.Json)
        header(HttpHeaders.Authorization, "Token ${settings.getString(LinkdingSettings.TOKEN)}")
    }
    install(HttpTimeout) { requestTimeoutMillis = 3000 }
}
