package com.example.qollabapp.model

data class Room_Quiz(
    val id: Int,
    val room: Room,
    val user: User,
    val correct: Int,
    val Incorrect: Int,
    val rank: Int,
)