package com.example.qollabapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qollabapp.R
import com.example.qollabapp.model.Room_Quiz
import com.example.qollabapp.viewModel.RqVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailHistoryScreen(navController: NavController, id: Int, rqVM: RqVM) {
    val rq = rqVM.rq.collectAsState()
    val room = rq.value.find { rq.value.first().room.id == id }

    room?.let {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .width(80.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(color = colorResource(R.color.primary_base))
                                    .size(40.dp)
                            ) {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowLeft,
                                        contentDescription = null,
                                        tint = colorResource(R.color.white)
                                    )

                                    Text(
                                        text = "Back",
                                        color = colorResource(R.color.white),
                                        fontSize = 12.sp,
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(30.dp))

                            Text(
                                text = "${room.room.name_room} Leaderboard",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.primary_600)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn (
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(rq.value) { data ->
                        CardHistory(roomQuiz = data)
                    }
                }
            }
        }
    }
}

@Composable
fun CardHistory(roomQuiz: Room_Quiz) {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.primary_base),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(70.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = roomQuiz.user.username,
                contentScale = ContentScale.Fit,
                modifier = Modifier.clip(CircleShape).size(150.dp).offset(x = -40.dp)
            )

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 8.dp).offset(x = -70.dp)
            ) {
                Text(
                    text = roomQuiz.user.username,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.white)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${roomQuiz.correct} points",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.white)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "0${roomQuiz.rank}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHistoryScreenPreview() {
    val navController = rememberNavController()
    val rqVM = RqVM()
    val roomList = rqVM.rq.collectAsState()
    val room = roomList.value
    if (room.isNotEmpty()){
        DetailHistoryScreen(
            navController = navController,
            room[0].id,
            rqVM = rqVM
        )
    }
}