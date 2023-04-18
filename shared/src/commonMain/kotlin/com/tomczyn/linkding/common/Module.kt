@file:Suppress("RemoveExplicitTypeArguments")

package com.tomczyn.linkding.common

import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.data.LinkdingRepository
import com.tomczyn.linkding.data.local.DriverFactory
import com.tomczyn.linkding.data.local.LinkdingDao
import com.tomczyn.linkding.data.local.createDatabase
import com.tomczyn.linkding.data.remote.LinkdingService
import com.tomczyn.linkding.database.LinkdingDatabase
import com.tomczyn.linkding.features.home.usecase.GetHomeScreenTagsUseCase
import com.tomczyn.linkding.features.login.LoginUseCase
import io.ktor.client.*
import org.koin.dsl.module

fun appModule() = module {
    factory { LinkdingHttpClient(get<Settings>()) }
    factory { LinkdingService(get<HttpClient>(), get<Settings>()) }
    factory { LoginUseCase(get<Settings>()) }
    single<LinkdingDatabase> { createDatabase(get<DriverFactory>()) }
    single { LinkdingDao(get<LinkdingDatabase>()) }
    single { LinkdingRepository(get<LinkdingDao>(), get<LinkdingService>()) }
    factory { GetHomeScreenTagsUseCase(get<LinkdingRepository>()) }
}
