package com.tomczyn.linkding

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform