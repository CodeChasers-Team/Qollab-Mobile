package com.example.qollabapp.repository

import com.example.qollabapp.model.Room
import com.example.qollabapp.model.TypeRoom
import com.example.qollabapp.viewModel.UserVm

class RoomRepository {
    val userVm = UserVm()
    val user = userVm.user
    private val rooms = listOf(
        Room(1 , 123, "Room 1", "Belajar Matematika", TypeRoom.Group, user.value, user.value[0], "28 October 2024"),
        Room(2 , 345, "Room 2", "Belajar Algoritma", TypeRoom.Group, user.value, user.value[1], "29 October 2024"),
        Room(3 , 678, "Room 3", "Belajar Web Development", TypeRoom.Individual, user.value, user.value[2], "30 October 2024"),
        Room(4 , 91011, "Room 4", "Belajar Mobile Development", TypeRoom.Group, user.value, user.value[3], "31 October 2024"),
        Room(5 , 121314, "Room 5", "Belajar AI Engineer", TypeRoom.Individual, user.value, user.value[4], "31 October 2024"),
        Room(6 , 151617, "Room 6", "Belajar Database Engineer", TypeRoom.Group, user.value, user.value[2], "32 October 2024"),
    )

    fun getRooms() : List<Room>{
        return rooms
    }
}