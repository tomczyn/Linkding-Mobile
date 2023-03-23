package com.tomczyn.linkding.common

import com.tomczyn.linkding.LinkdingRepository
import org.koin.dsl.module

private const val url = "https://linkding.nas.narvi.sh"

fun appModule() = module {
    single { LinkdingHttpClient() }
    single { LinkdingRepository(get(), url) }
}
