package com.tomczyn.linkding.features.home

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.tomczyn.linkding.data.LinkdingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : KMMViewModel(), KoinComponent {

    private val repo: LinkdingRepository by inject()

    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(viewModelScope, HomeState())

    @NativeCoroutinesState
    val state: StateFlow<HomeState> get() = _state

    init {
        fetchTags()
    }

    fun refresh() {
        fetchTags()
    }

    fun resetError() {
        _state.update { it.copy(error = false) }
    }

    private fun fetchTags() {
        repo.getTags()
            .onEach { tags -> _state.update { it.copy(tags = tags) } }
            .catch { _state.update { it.copy(error = true) } }
            .launchIn(viewModelScope.coroutineScope)
    }
}
