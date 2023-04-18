package com.tomczyn.linkding.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tomczyn.linkding.database.LinkdingDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(LinkdingDatabase.Schema, "linkding_database.db")
    }
}