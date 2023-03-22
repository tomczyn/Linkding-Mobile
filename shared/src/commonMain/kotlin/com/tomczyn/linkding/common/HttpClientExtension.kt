package com.tomczyn.linkding.common

import arrow.core.left
import arrow.core.right
import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.*
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal suspend inline fun <reified RES : Any, reified ERR : Any, reified REQ : Any> HttpClient.put(
    url: String,
    body: REQ,
    builder: HttpRequestBuilder.() -> Unit = {}
): Response<ERR, RES> = request {
    put(url) {
        setBody(body)
        builder()
    }
}

internal suspend inline fun <reified RES : Any, reified ERR : Any, reified REQ : Any> HttpClient.patch(
    url: String,
    body: REQ,
    builder: HttpRequestBuilder.() -> Unit = {}
): Response<ERR, RES> = request {
    patch(url) {
        setBody(body)
        builder()
    }
}

internal suspend inline fun <reified RES : Any, reified ERR : Any> HttpClient.delete(
    url: String,
    builder: HttpRequestBuilder.() -> Unit = {}
): Response<ERR, RES> = request {
    delete(url) {
        builder()
    }
}

internal suspend inline fun <reified RES : Any, reified ERR : Any> HttpClient.get(
    url: String,
    builder: HttpRequestBuilder.() -> Unit = {}
): Response<ERR, RES> = request {
    get(url) {
        setBody(Json.encodeToString(""))
        builder()
    }
}

internal suspend inline fun <reified RES : Any, reified ERR : Any, reified REQ : Any> HttpClient.post(
    url: String,
    body: REQ,
    builder: HttpRequestBuilder.() -> Unit = {}
): Response<ERR, RES> = request {
    post(url) {
        setBody(body)
        builder()
    }
}

internal suspend inline fun <reified RES : Any, reified ERR : Any> request(
    request: () -> HttpResponse
): Response<ERR, RES> = runCatching { request() }.fold(
    onSuccess = { response ->
        when (response.status.value) {
            in 200..299 -> response.body<RES>().right()
            else -> runCatching { response.body<ERR>() }
                .fold(
                    onSuccess = {
                        Logger.e(response.body())
                        RequestError.Api(response.status.value, it)
                    },
                    onFailure = {
                        if (it is CancellationException) throw it
                        else {
                            Logger.e(it.message.orEmpty())
                            RequestError.Exception(it)
                        }
                    }
                ).left()
        }
    },
    onFailure = {
        if (it is CancellationException) throw it
        else {
            Logger.e(it.message.orEmpty())
            RequestError.Exception(it).left()
        }
    }
)

internal fun HttpClient.clearTokens() = this.plugin(Auth).providers
    .filterIsInstance<BearerAuthProvider>()
    .forEach { it.clearToken() }
