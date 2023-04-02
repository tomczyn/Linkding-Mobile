package com.tomczyn.linkding.android.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tomczyn.linkding.Settings
import com.tomczyn.linkding.android.ui.home.homeGraph
import com.tomczyn.linkding.android.ui.home.homeRoute
import com.tomczyn.linkding.android.ui.login.loginGraph
import com.tomczyn.linkding.android.ui.login.loginRoute
import com.tomczyn.linkding.isUserLoggedIn

@Composable
fun LinkdingNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    settings: Settings
) {
    val startDestination = if (settings.isUserLoggedIn) homeRoute else loginRoute
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        loginGraph()
        homeGraph()
    }
}
