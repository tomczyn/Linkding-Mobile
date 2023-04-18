package com.tomczyn.linkding.android.ui.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val loginRoute: String = "login_route"

fun NavGraphBuilder.loginGraph(
    goToHome: () -> Unit
) {
    composable(route = loginRoute) {
        LoginRoute(goToHome = goToHome)
    }
}
