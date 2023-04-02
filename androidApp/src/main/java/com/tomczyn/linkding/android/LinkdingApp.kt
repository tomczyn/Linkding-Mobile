package com.tomczyn.linkding.android

import android.app.Application
import com.tomczyn.linkding.android.common.androidModule
import com.tomczyn.linkding.common.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class LinkdingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LinkdingApp)
            androidLogger()
            modules(appModule() + androidModule)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
