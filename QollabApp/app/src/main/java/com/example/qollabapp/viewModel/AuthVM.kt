package com.example.qollabapp.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.qollabapp.screen.Screen
import com.example.qollabapp.utils.AuthUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthVM(navController: NavController): ViewModel() {
    var fullName by mutableStateOf("")
    var fullNameError by mutableStateOf<String?>(null)
    var username by mutableStateOf("")
    var usernameError by mutableStateOf<String?>(null)
    var email by mutableStateOf("")
    var emailError by mutableStateOf<String?>(null)
    var password by mutableStateOf("")
    var passwordError by mutableStateOf<String?>(null)
    var isPasswordVisible by mutableStateOf(false)

    fun isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onfullNameChange(new_fullName: String){
        fullName = new_fullName
    }

    fun onusernameChange(new_username: String){
        username = new_username
    }

    fun onEmailChange(new_email: String){
        email = new_email
    }

    fun onPasswordChange(new_password: String){
        password = new_password
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun validatefullName() {
        fullNameError = when{
            fullName.isEmpty() -> "Full Name is required"
            else -> null
        }
    }

    fun validateUsername() {
        usernameError = when{
            username.isEmpty() -> "Username is required"
            else -> null
        }
    }

     fun validateEmail() {
        emailError = when{
            !isValidEmail() -> "Email format invalid"
            email.isEmpty() -> "Email is required"
            else -> null
        }
    }


     fun validatePassword() {
        passwordError = when {
            password.isEmpty() -> "Password is required"
            password.length < 6 -> "Password must be 6 characters"
            else -> null
        }
    }

    fun validateFieldLogin() : Boolean {
        validateEmail()
        validatePassword()

        return listOf(
            emailError,
            passwordError
        ).all { it == null }
    }

    fun validateFieldRegister() : Boolean {
        validatefullName()
        validateUsername()
        validateEmail()
        validatePassword()

        return listOf(
            fullNameError,
            usernameError,
            emailError,
            passwordError
        ).all { it == null }
    }

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> get() = _authState

    fun register(navController: NavController, context: Context) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            if (!validateFieldRegister()){
                _authState.value = AuthState.Error("Field is required")
                return@launch
            }
            delay(1000)
            _authState.value = AuthState.Success("Register Success")
            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Login.route){
                popUpTo(Screen.Register.route) {inclusive = true}
            }
        }
    }

    fun login(navController: NavController, context: Context){
        val authUtils = AuthUtils(context = context)
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            if (!validateFieldLogin()){
                _authState.value = AuthState.Error("Field Is Required")
                return@launch
            }
            delay(1000)
            authUtils.LoggedIn()
            _authState.value = AuthState.Success("Login Success")
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Room.route){
                popUpTo(0) {inclusive = true}
            }
        }
    }



}

sealed class AuthState(){
    object Idle: AuthState()
    object Loading: AuthState()
    data class Success(val msg: String): AuthState()
    data class Error(val msg: String): AuthState()
}