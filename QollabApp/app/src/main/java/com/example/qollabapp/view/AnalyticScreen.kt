package com.example.qollabapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qollabapp.R
import com.example.qollabapp.component.BottomAppBar.BottomAppBarUI
import com.example.qollabapp.model.Room
import com.example.qollabapp.model.Room_Quiz
import com.example.qollabapp.model.TypeRoom
import com.example.qollabapp.viewModel.RqVM
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticScreen(navController: NavController, rqVM: RqVM) {
    var isGroup by remember { mutableStateOf(false) }
    var isIndividu by remember { mutableStateOf(false) }
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
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "User Analytic",
                        fontSize = 16.sp,
                        color = colorResource(R.color.primary_600),
                        textDecoration = if (isIndividu) TextDecoration.Underline else TextDecoration.None,
                        modifier = Modifier.clickable {
                            isIndividu = true
                            isGroup = false
                        }
                    )
                    Text(
                        text = "Group Analytic",
                        fontSize = 16.sp,
                        color = colorResource(R.color.primary_600),
                        textDecoration = if (isGroup) TextDecoration.Underline else TextDecoration.None,
                        modifier = Modifier.clickable {
                            isIndividu = false
                            isGroup = true
                        }
                    )
                }
                 Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                     if (isGroup){
                         GroupTableAnalytic(rqVM = rqVM)
                     } else {
                         UserTableAnalytic(rqVM = rqVM)
                     }
                 }
            }
        }
    }
}

@Composable
fun GroupTableAnalytic(rqVM: RqVM) {
    val rq = rqVM.rq.collectAsState()
    if (rq.value.isNotEmpty()){
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val header = listOf("attendance", "correct", "incorrect", "ranking")
            Row  (
                modifier = Modifier.fillMaxWidth()
            ){
                header.forEach{ headers ->
                    Text(
                        text = headers,
                        modifier = Modifier
                            .padding(8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                }
            }

            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)

            rq.value.forEach{ data ->
                if (data.room.type_room == TypeRoom.Group){
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = data.room.name_room,
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${data.correct}%",
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${data.Incorrect}%",
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${data.rank}%",
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)
                }
            }

        }
    } else{
        CircularProgressIndicator()
    }
}

@Composable
fun UserTableAnalytic(rqVM: RqVM) {
    val rq = rqVM.rq.collectAsState()
    if (rq.value.isNotEmpty()){
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val header = listOf("attendance", "correct", "incorrect", "ranking")
            Row  (
                modifier = Modifier.fillMaxWidth()
            ){
                header.forEach{ headers ->
                    Text(
                        text = headers,
                        modifier = Modifier
                            .padding(8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                }
            }

            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)

            rq.value.forEach{ data ->
                if (data.room.type_room == TypeRoom.Individual){
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = data.room.name_room,
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${data.correct}%",
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${data.Incorrect}%",
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${data.rank}%",
                            modifier = Modifier
                                .padding(24.dp),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)
                }
            }

        }
    } else{
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun AnalyticScreenPreview() {
    val navController = rememberNavController()
    val rqVM = RqVM()
    AnalyticScreen(navController = navController, rqVM = rqVM)
}