package com.tomczyn.linkding

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    val settings: Settings by inject()
}
