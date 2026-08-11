package com.decloudius.jmodupe.presentation.screens.beranda

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decloudius.jmodupe.domain.model.User
import com.decloudius.jmodupe.domain.repository.UserRepository
import kotlinx.coroutines.launch

class BerandaViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            _user.value = userRepository.getUser()
        }
    }
}
