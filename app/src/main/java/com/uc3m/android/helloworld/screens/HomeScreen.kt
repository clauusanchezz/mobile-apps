package com.uc3m.android.helloworld.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to StudyStorm", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.navigate("subjects")  }) {
                Text("Subjects")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate("study_plans")}) {
                Text("Study Plans & Tips")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate("study_progress") }) {
                Text("Study Progress")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate("offline_mode")}) {
                Text("Offline Mode")
            }
        }
    }
}
