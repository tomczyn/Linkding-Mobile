package com.tomczyn.linkding.features.login

import co.touchlab.kermit.Logger
import com.tomczyn.linkding.LinkdingSettings
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.common.Result
import com.tomczyn.linkding.common.provideEngine
import com.tomczyn.linkding.setString
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*

class LoginUseCase(
    private val settings: Settings,
) : suspend (String, String) -> Result {

    override suspend fun invoke(host: String, token: String): Result {
        val client = HttpClient(provideEngine())
        val cleanedHost = host.removeSuffix("/")
        try {
            val result = client.get("$cleanedHost/api/tags") {
                header(HttpHeaders.Authorization, "Token $token")
            }
            if (result.status.isSuccess()) {
                settings.setString(LinkdingSettings.HOST, host)
                settings.setString(LinkdingSettings.TOKEN, token)
                return Result.Success
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            Logger.e { "Error $e" }
        }
        return Result.Failure
    }
}
