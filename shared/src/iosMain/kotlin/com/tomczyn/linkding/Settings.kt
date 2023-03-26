package com.tomczyn.linkding

import platform.Foundation.NSUserDefaults

actual class Settings {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    actual fun getString(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    actual fun setString(key: String, value: String) {
        userDefaults.setObject(value, forKey = key)
        userDefaults.synchronize()
    }
}
