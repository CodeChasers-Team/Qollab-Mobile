package com.example.qollabapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.qollabapp.screen.Screen
import com.example.qollabapp.ui.theme.QollabAppTheme
import com.example.qollabapp.utils.AuthUtils
import com.example.qollabapp.view.AnalyticScreen
import com.example.qollabapp.view.DetailHistoryScreen
import com.example.qollabapp.view.HistoryScreen
import com.example.qollabapp.view.LoginScreen
import com.example.qollabapp.view.RegisterScreen
import com.example.qollabapp.view.RoomScreen
import com.example.qollabapp.viewModel.AuthVM
import com.example.qollabapp.viewModel.RoomVM
import com.example.qollabapp.viewModel.RqVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            QollabAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainNavigation()
                }
            }
        }
    }

    private val authUtils by lazy { AuthUtils(this) }

    @Composable
    fun MainNavigation() {
        val navController = rememberNavController()
        val startPage = if (authUtils.isLoggedIn()){
            Screen.Room.route
        } else
            Screen.Login.route

        NavHost(
            navController = navController,
            startDestination = startPage,
        ) {
            composable(Screen.Login.route){
                val authVM = AuthVM(navController)
                LoginScreen(navController = navController, authVM = authVM)
            }

            composable(Screen.Register.route){
                val authVM = AuthVM(navController = navController)
                RegisterScreen(navController = navController, authVM = authVM)
            }

            composable(Screen.Room.route) {
                val roomVM = RoomVM()
                RoomScreen(navController = navController, roomVM = roomVM)
            }

            composable(Screen.Analytic.route){
                val rqVM = RqVM()
                AnalyticScreen(navController = navController, rqVM = rqVM)
            }

            composable(Screen.History.route){
                val roomVM = RoomVM()
                HistoryScreen(navController = navController, roomVM = roomVM)
            }

            composable(
                route = Screen.DetailHistory.route,
                arguments = listOf(navArgument("id") {type = NavType.IntType})
            ) { navBackStackEntry ->
                val rqVM = RqVM()
                val id = navBackStackEntry.arguments?.getInt("id")
                id?.let {
                    DetailHistoryScreen(navController = navController, id = id, rqVM = rqVM)
                }
            }
        }
    }
}