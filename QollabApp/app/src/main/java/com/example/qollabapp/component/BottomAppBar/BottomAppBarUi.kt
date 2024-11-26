package com.example.qollabapp.component.BottomAppBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.qollabapp.R
import com.example.qollabapp.screen.Screen

@Composable
fun BottomAppBarUI(navController: NavController) {
    val currenRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    Surface(
        shadowElevation = 4.dp
    ) {
        BottomAppBar(
            containerColor = Color.White,
            modifier = Modifier.height(75.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Room.route){
                                popUpTo(Screen.Room.route) {inclusive = true}
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_room),
                            contentDescription = "Room",
                            modifier = Modifier.size(24.dp),
                            tint = if (currenRoute == Screen.Room.route) colorResource(id = R.color.primary_base) else colorResource(id = R.color.primary_300)
                        )
                    }
                    Text(
                        text = "Room",
                        color = if (currenRoute == Screen.Room.route) colorResource(id = R.color.primary_base) else colorResource(id = R.color.primary_300),
                        fontSize = 12.sp
                    )
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Analytic.route){
                                popUpTo(Screen.Analytic.route) {inclusive = true}
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_analytic),
                            contentDescription = "Analytic",
                            modifier = Modifier.size(24.dp),
                            tint = if (currenRoute == Screen.Analytic.route) colorResource(id = R.color.primary_base) else colorResource(id = R.color.primary_300)
                        )
                    }
                    Text(
                        text = "Analytic",
                        color = if (currenRoute == Screen.Analytic.route) colorResource(id = R.color.primary_base) else colorResource(id = R.color.primary_300),
                        fontSize = 12.sp
                    )
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.History.route){
                                popUpTo(Screen.History.route) {inclusive = true}
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_history),
                            contentDescription = "History",
                            modifier = Modifier.size(24.dp),
                            tint = if (currenRoute == Screen.History.route) colorResource(id = R.color.primary_base) else colorResource(id = R.color.primary_300)
                        )
                    }
                    Text(
                        text = "History",
                        color = if (currenRoute == Screen.History.route) colorResource(id = R.color.primary_base) else colorResource(id = R.color.primary_300),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun BottomAppBarUIPreview() {
    val navController = rememberNavController()
    BottomAppBarUI(navController = navController)
}