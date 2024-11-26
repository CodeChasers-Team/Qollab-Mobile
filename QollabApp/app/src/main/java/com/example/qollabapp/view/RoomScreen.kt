package com.example.qollabapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.rotationMatrix
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qollabapp.R
import com.example.qollabapp.component.BottomAppBar.BottomAppBarUI
import com.example.qollabapp.component.Button.ButtonUI
import com.example.qollabapp.component.Field.TextFieldUI
import com.example.qollabapp.model.Room
import com.example.qollabapp.viewModel.RoomVM
import com.example.qollabapp.viewModel.UserVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(navController: NavController,roomVM: RoomVM) {
    val context = LocalContext.current
    val rooms = roomVM.room.collectAsState()
    Scaffold (
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
        },
        bottomBar = {
            BottomAppBarUI(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Join Room Using Code",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.primary_600)
                )

                Spacer(modifier = Modifier.size(15.dp))

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = roomVM.code,
                        onValueChange = {
                            roomVM.onCodeChange(it)
                            roomVM.validateCodeRoom()
                        },
                        placeholder = {
                            Text("Enter Code")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            roomVM.enterCode(navController = navController, context = context)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_base)),
                        elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
                    ) {
                        Text("Join Room", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "List Room",
                    color = colorResource(R.color.primary_600),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.size(20.dp))

                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(rooms.value) { room ->
                        RoomCard(room = room){}
                    }
                }

            }
        }
    }
}

@Composable
fun RoomCard(room: Room, onClick: () -> Unit) {
    val userVm = UserVm()
    val users = userVm.user.collectAsState()
    val participant = users.value.size ?: 0
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD),
            contentColor = Color.White
        ),
        border = BorderStroke(2.dp, colorResource(R.color.primary_base)),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(120.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = room.name_room,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.primary_600)
            )

            Text(
                text = room.user.username,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.primary_600)
            )

            Text(
                text = room.desc_room,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 8.dp),
                color = colorResource(R.color.primary_600)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "${participant} participant",
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    color = colorResource(R.color.primary_600)
                )
                Text(
                    text = "Join",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = colorResource(R.color.primary_600),
                    modifier = Modifier.clickable {

                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoomScreenPreview() {
    val navController = rememberNavController()
    val roomVM = RoomVM()
    RoomScreen(navController = navController, roomVM = roomVM)
}