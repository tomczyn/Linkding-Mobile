package com.tomczyn.linkding.android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomczyn.linkding.LinkdingRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val linkdingRepo: LinkdingRepository
) : ViewModel() {

    fun login(host: String, token: String) {
    }
}
