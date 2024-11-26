package com.example.qollabapp.repository

import com.example.qollabapp.model.Room_Quiz
import com.example.qollabapp.viewModel.RoomVM
import com.example.qollabapp.viewModel.UserVm

class RoomQuizRepository {
    val roomVM = RoomVM()
    val room = roomVM.room.value
    val userVm = UserVm()
    val user = userVm.user.value
    private val rq = listOf(
        Room_Quiz(1, room[0], user[1],70, 30, 2),
        Room_Quiz(2, room[1], user[2],60, 40, 3),
        Room_Quiz(3, room[2], user[3],50, 50, 4),
        Room_Quiz(4, room[3], user[4],40, 60, 5),
    )

    fun getRQ() : List<Room_Quiz>{
        return rq
    }
}