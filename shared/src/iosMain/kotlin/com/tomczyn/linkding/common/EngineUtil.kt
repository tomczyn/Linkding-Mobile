package com.tomczyn.linkding.common

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun provideEngine(): HttpClientEngineFactory<*> = Darwin
