package com.example.qollabapp.repository

import com.example.qollabapp.model.User

class UserRepository {
    private val users = listOf(
        User(1, "Daniel Manalu", "Daman", "daniel@gmail.com", "daman123"),
        User(2, "Oktavia Simatupang", "Okta", "oktavia@gmail.com", "okta123"),
        User(3, "Fauzan", "fauzan", "fauzan@gmail.com", "fauzan123"),
        User(4, "Ageng", "ageng", "ageng@gmail.com", "ageng123"),
        User(5, "Dzikri", "dzikri", "dzikri@gmail.com", "dzikri123"),
    )

    fun getUsers():List<User>{
        return users
    }
}