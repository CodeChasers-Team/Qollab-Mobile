package com.example.qollabapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qollabapp.model.Room_Quiz
import com.example.qollabapp.repository.RoomQuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RqVM: ViewModel() {
    val roomQuizRepository = RoomQuizRepository()
    private val _rq = MutableStateFlow<List<Room_Quiz>>(emptyList())
    val rq: StateFlow<List<Room_Quiz>>get() = _rq

    init {
        getRq()
    }

    fun getRq(){
        viewModelScope.launch {
            _rq.value = roomQuizRepository.getRQ()
        }
    }
}