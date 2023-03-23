package com.tomczyn.linkding

import platform.darwin.NSObject

actual typealias Preferences = NSObject

actual fun Preferences.getInt(key: String) : Int {
    TODO()
}

actual fun Preferences.setInt(key: String, value: Int) {

}

actual fun Preferences.getString(key: String) {
    TODO()
}

actual fun Preferences.setString(key: String, value: String) {

}
