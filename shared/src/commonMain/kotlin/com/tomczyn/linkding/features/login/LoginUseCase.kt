package com.tomczyn.linkding.features.login

import com.tomczyn.linkding.Settings

class LoginUseCase(
    private val settings: Settings
) : suspend (String, String) -> String {

    override suspend fun invoke(host: String, token: String): String {
        settings.setString(host_key, host)
        settings.setString(token_key, token)
        return "success"
    }

    companion object {
        private const val host_key = "host"
        private const val token_key = "token"
    }
}
