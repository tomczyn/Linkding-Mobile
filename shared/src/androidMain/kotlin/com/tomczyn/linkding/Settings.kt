package com.tomczyn.linkding

import android.content.Context

actual class Settings(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("linkding_prefs", Context.MODE_PRIVATE)

    actual fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    actual fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}
