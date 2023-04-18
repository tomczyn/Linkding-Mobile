package com.tomczyn.linkding.android.common

import android.app.Application
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.data.local.DriverFactory
import com.tomczyn.linkding.features.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { LoginViewModel() }
    single { Settings(get<Application>()) }
    single { DriverFactory(get<Application>()) }
}
