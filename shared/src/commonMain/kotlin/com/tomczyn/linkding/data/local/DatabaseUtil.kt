package com.tomczyn.linkding.data.local

import com.tomczyn.linkding.database.LinkdingDatabase

fun createDatabase(driverFactory: DriverFactory): LinkdingDatabase {
    val driver = driverFactory.createDriver()
    return LinkdingDatabase(driver)
}
