package com.tomczyn.linkding.common

import io.ktor.client.*
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

@Suppress("FunctionName")
@OptIn(ExperimentalSerializationApi::class)
fun LinkdingHttpClient(): HttpClient = HttpClient(CIO) {
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
    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens(
                    accessToken = TODO(),
                    refreshToken = TODO()
                )
            }
        }
    }
    defaultRequest { contentType(ContentType.Application.Json) }
    install(HttpTimeout) { requestTimeoutMillis = 3000 }
}