package com.tomczyn.linkding

import com.tomczyn.linkding.features.login.LoginState
import com.tomczyn.linkding.features.login.LoginViewModel

val LoginViewModel.loginState: LoginState get() = state.value
