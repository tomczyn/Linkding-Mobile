package com.tomczyn.linkding

expect class Settings {
    fun getString(key: String): String?
    fun setString(key: String, value: String)
}
