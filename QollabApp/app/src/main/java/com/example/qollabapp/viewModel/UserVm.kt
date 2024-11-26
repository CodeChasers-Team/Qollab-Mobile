package com.example.qollabapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qollabapp.model.User
import com.example.qollabapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserVm: ViewModel() {
    val userRepository = UserRepository()
    private val _user = MutableStateFlow<List<User>>(emptyList())
    val user: StateFlow<List<User>>get() = _user

    init {
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            _user.value = userRepository.getUsers()
        }
    }
}