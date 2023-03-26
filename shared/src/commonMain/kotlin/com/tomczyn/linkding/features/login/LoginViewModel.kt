package com.tomczyn.linkding.features.login

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : KMMViewModel(), KoinComponent {

    private val loginUseCase: LoginUseCase by inject()

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state

    fun login(host: String, token: String) {
        viewModelScope.coroutineScope.launch {
            _state.update { it.copy(isLoading = true) }
            loginUseCase(host, token)
            _state.update { it.copy(isLoading = false) }
        }
    }
}
