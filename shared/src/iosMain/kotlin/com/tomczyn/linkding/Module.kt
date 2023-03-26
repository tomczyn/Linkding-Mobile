package com.tomczyn.linkding

import com.tomczyn.linkding.common.appModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    val koinApp = startKoin {
        modules(appModule() + iosModule)
    }.koin
}

val iosModule = module {
    single { Settings() }
}
