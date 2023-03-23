package com.tomczyn.linkding.android.common

import com.tomczyn.linkding.android.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { LoginViewModel(get()) }
}
