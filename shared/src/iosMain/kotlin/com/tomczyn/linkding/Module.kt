package com.tomczyn.linkding

import com.tomczyn.linkding.common.appModule
import com.tomczyn.linkding.data.local.DriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    val koinApp = startKoin {
        modules(appModule() + iosModule)
    }.koin
}

val iosModule = module {
    single { Settings() }
    single { DriverFactory() }
}
