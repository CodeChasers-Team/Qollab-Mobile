package com.example.qollabapp.utils

import android.content.Context

class AuthUtils(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean("complete", false)
    }

    fun LoggedIn(){
        return sharedPreferences.edit()
            .putBoolean("complete", true)
            .apply()
    }
}