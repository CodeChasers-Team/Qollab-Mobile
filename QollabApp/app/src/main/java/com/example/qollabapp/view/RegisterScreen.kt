package com.example.qollabapp.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qollabapp.R
import com.example.qollabapp.component.Button.ButtonUI
import com.example.qollabapp.component.Field.TextFieldUI
import com.example.qollabapp.screen.Screen
import com.example.qollabapp.viewModel.AuthVM

@Composable
fun RegisterScreen(navController: NavController, authVM: AuthVM) {
    val context = LocalContext.current
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "Welcomeback to",
                    fontWeight = FontWeight.Medium,
                    fontSize = 40.sp,
                    color = colorResource(id = R.color.primary_600)
                )

                Text(
                    text = "Qollab",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = colorResource(id = R.color.primary_base)
                )

                Text(
                    text = "Selamat datang di Qollab tempat Anda berkolaborasi untuk mengasah kemampuan",
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 1.sp,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.primary_600)
                )

                Column (
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    TextFieldUI(
                        value = authVM.fullName,
                        onValueChange = {
                            authVM.onfullNameChange(it)
                            authVM.validatefullName()
                        },
                        label = "Full Name",
                        keyboardType = KeyboardType.Text,
                        errorMessage = authVM.fullNameError
                    )

                    TextFieldUI(
                        value = authVM.username,
                        onValueChange = {
                            authVM.onusernameChange(it)
                            authVM.validateUsername()
                        },
                        label = "Username",
                        keyboardType = KeyboardType.Text,
                        errorMessage = authVM.usernameError
                    )

                    TextFieldUI(
                        value = authVM.email,
                        onValueChange = {
                            authVM.onEmailChange(it)
                            authVM.validateEmail()
                        },
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        errorMessage = authVM.emailError
                    )

                    TextFieldUI(
                        value = authVM.password,
                        onValueChange = {
                            authVM.onPasswordChange(it)
                            authVM.validatePassword()
                        },
                        label = "Password",
                        isPassword = true,
                        isPasswordVisible = authVM.isPasswordVisible,
                        onPasswordVisibilityToggle = authVM::togglePasswordVisibility,
                        keyboardType = KeyboardType.Password,
                        errorMessage = authVM.passwordError
                    )

                    ButtonUI(
                        text = "Register"
                    ) {
                        authVM.register(navController = navController, context = context)

                    }
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "already have account? ",
                        color = Color.Black,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = "Login",
                        color = colorResource(id = R.color.primary_base),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Login.route){
                                popUpTo(Screen.Register.route) {inclusive = true}
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val authVM = AuthVM(navController = navController)
    RegisterScreen(navController = navController, authVM = authVM)

}