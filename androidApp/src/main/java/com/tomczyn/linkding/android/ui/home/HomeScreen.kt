package com.tomczyn.linkding.android.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomczyn.linkding.android.ui.common.AppScreen
import com.tomczyn.linkding.features.home.HomeState
import com.tomczyn.linkding.features.home.HomeViewModel

@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state
    )
}

@Composable
fun HomeScreen(
    state: HomeState
) {
    AppScreen {
        Text(text = state.tags.size.toString())
    }
}
