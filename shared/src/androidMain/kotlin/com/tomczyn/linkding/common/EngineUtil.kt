package com.tomczyn.linkding.common

import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*

actual fun provideEngine(): HttpClientEngineFactory<*> = CIO
