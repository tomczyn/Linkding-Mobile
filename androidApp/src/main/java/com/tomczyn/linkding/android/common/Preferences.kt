package com.tomczyn.linkding.android.common

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

actual typealias Preferences = Activity

actual fun Preferences.getInt(key: String): Int {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getInt(key, -1)
}


actual fun Preferences.setInt(key: String, value: Int) {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putInt(key, value)
    editor.apply()
}

actual fun Preferences.getString(key: String): String {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getString(key, "") ?: ""
}


actual fun Preferences.setString(key: String, value: String) {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putString(key, value)
    editor.apply()
}
