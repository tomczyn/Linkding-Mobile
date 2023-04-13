package com.tomczyn.linkding.android.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tomczyn.linkding.android.ui.login.LoginRoute

const val homeRoute: String = "home_route"

fun NavGraphBuilder.homeGraph() {
    composable(route = homeRoute) {
        HomeRoute()
    }
}
