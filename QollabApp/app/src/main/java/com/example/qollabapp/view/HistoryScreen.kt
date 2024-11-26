package com.example.qollabapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qollabapp.R
import com.example.qollabapp.component.BottomAppBar.BottomAppBarUI
import com.example.qollabapp.model.Room
import com.example.qollabapp.screen.Screen
import com.example.qollabapp.viewModel.RoomVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController, roomVM: RoomVM) {
    val rooms = roomVM.room.collectAsState()
    Scaffold(
        bottomBar = {
            BottomAppBarUI(navController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_logo),
                            contentDescription = "logo",
                            tint = colorResource(R.color.primary_base)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                item {
                    Text(
                        text = "History",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.primary_600)
                    )
                }

                items(rooms.value){ room ->
                    CardHistory(room = room) {
                        navController.navigate(Screen.DetailHistory.createRoute(id = room.id)){
                            popUpTo(Screen.History.route) {inclusive = true}
                        }
                    }
                }
                
            }
        }
    }
}

@Composable
fun CardHistory(room: Room, onClick: () -> Unit) {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.primary_600),
            contentColor = Color.White
        ),
        border = BorderStroke(2.dp, colorResource(R.color.primary_base)),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(100.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = room.name_room,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = room.user.username,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = room.createdAt,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "View Rankings",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        onClick()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    val navController = rememberNavController()
    val roomVM = RoomVM()
    HistoryScreen(navController = navController, roomVM = roomVM)
}
