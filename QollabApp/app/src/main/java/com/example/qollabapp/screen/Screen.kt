package com.example.qollabapp.screen

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object Room: Screen("room")
    object Analytic: Screen("analytic")
    object History: Screen("history")
    object DetailHistory : Screen("detail_history/{id}"){
        fun createRoute(id: Int) = "detail_history/$id"
    }
}