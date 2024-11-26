package com.example.qollabapp.model

data class Room(
    val id: Int,
    val code: Int,
    val name_room: String,
    val desc_room: String,
    val type_room: Enum<TypeRoom>,
    val participant: List<User>,
    val user: User,
    val createdAt: String,
)

enum class TypeRoom {
    Group, Individual
}