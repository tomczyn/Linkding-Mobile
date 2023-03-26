package com.tomczyn.linkding.android.common

import android.app.Application
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.android.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { LoginViewModel(get()) }
    single { Settings(get<Application>()) }
}
