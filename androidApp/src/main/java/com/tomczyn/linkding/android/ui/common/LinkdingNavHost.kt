package com.tomczyn.linkding.android.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tomczyn.linkding.android.ui.login.loginGraph
import com.tomczyn.linkding.android.ui.login.loginRoute

@Composable
fun LinkdingNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = loginRoute,
    ) {
        loginGraph()
    }
}

