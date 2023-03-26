@file:Suppress("RemoveExplicitTypeArguments")

package com.tomczyn.linkding.common

import com.tomczyn.linkding.LinkdingRepository
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.features.login.LoginUseCase
import io.ktor.client.*
import org.koin.dsl.module

private const val url = "https://linkding.nas.narvi.sh"

fun appModule() = module {
    factory { LinkdingHttpClient(get<Settings>()) }
    factory { LinkdingRepository(get<HttpClient>(), get<Settings>()) }
    factory { LoginUseCase(get<Settings>()) }
}
