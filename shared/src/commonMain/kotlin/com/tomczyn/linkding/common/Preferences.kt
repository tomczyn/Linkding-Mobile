package com.tomczyn.linkding.common

expect class Preferences

expect fun Preferences.getInt(key: String) : Int
expect fun Preferences.setInt(key: String, value: Int)
expect fun Preferences.getString(key: String) : String
expect fun Preferences.setString(key: String, value: String)
