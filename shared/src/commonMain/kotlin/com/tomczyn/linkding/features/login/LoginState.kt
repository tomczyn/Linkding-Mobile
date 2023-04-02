package com.tomczyn.linkding.features.login

data class LoginState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val goToHome: Boolean = false
)
