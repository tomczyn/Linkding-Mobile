package com.tomczyn.linkding.features.home

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

open class HomeViewModel : KMMViewModel(), KoinComponent {

    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(viewModelScope, HomeState())

    @NativeCoroutinesState
    val state: StateFlow<HomeState> get() = _state

}
