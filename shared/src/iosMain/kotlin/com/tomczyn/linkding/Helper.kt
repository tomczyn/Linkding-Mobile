package com.tomczyn.linkding

import com.tomczyn.linkding.common.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val koinApp = startKoin {
        modules(appModule())
    }.koin
}

class LinkdingRepositoryHelper : KoinComponent {
    val repository: LinkdingRepository by inject()
}
