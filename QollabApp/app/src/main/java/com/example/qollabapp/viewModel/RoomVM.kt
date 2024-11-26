package com.example.qollabapp.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.qollabapp.model.Room
import com.example.qollabapp.repository.RoomRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomVM: ViewModel() {
    var code by mutableStateOf("")
    var codeError by mutableStateOf<String?>(null)

    fun onCodeChange(new_code: String){
        code = new_code
    }

    fun validateCodeRoom() {
        codeError = when{
            code.isEmpty() -> "You must fill the code room"
            else -> null
        }
    }

    fun validateFieldCode(): Boolean{
        validateCodeRoom()

        return codeError == null
    }

    private val _roomState = MutableStateFlow<RoomState>(RoomState.Idle)
    val roomState: StateFlow<RoomState> get() = _roomState

    fun enterCode(navController: NavController, context: Context) {
        _roomState.value = RoomState.Loading
        viewModelScope.launch {
            if (!validateFieldCode()){
                _roomState.value = RoomState.Error("Field is required")
                return@launch
            }
            delay(1000)
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    val roomRepository = RoomRepository()
    private val _room = MutableStateFlow<List<Room>>(emptyList())
    val room: StateFlow<List<Room>> get() = _room

    init {
        getRoom()
    }

    fun getRoom() {
        viewModelScope.launch {
            _room.value = roomRepository.getRooms()
        }
    }
}

sealed class RoomState(){
    object Idle: RoomState()
    object Loading: RoomState()
    data class Success(val msg: String): RoomState()
    data class Error(val msg: String): RoomState()
}