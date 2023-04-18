package com.tomczyn.linkding.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tomczyn.linkding.database.LinkdingDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(LinkdingDatabase.Schema, context, "linkding_database.db")
    }
}
