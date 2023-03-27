package com.tomczyn.linkding.features.login

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.tomczyn.linkding.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class LoginViewModel : KMMViewModel(), KoinComponent {

    private val loginUseCase: LoginUseCase by inject()

    private val _state: MutableStateFlow<LoginState> =
        MutableStateFlow(viewModelScope, LoginState())

    @NativeCoroutinesState
    val state: StateFlow<LoginState> get() = _state

    fun login(host: String, token: String) {
        viewModelScope.coroutineScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (loginUseCase(host, token)) {
                Result.Failure -> _state.update { it.copy(error = true) }
                Result.Success -> _state.update { it.copy(goToHome = true) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun resetGoToHome() {
        _state.update { it.copy(goToHome = false) }
    }

    fun resetError() {
        _state.update { it.copy(error = false) }
    }
}
