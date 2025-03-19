package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OfflineModeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Subjects", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }) {
                Text("Maths")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {  }) {
                Text("Geography")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {  }) {
                Text("History")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { }) {
                Text("English")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }) {
                Text("Spanish")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }) {
                Text("Music")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }) {
                Text("Biology")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }) {
                Text("Physics")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }) {
                Text("Art")
            }
        }
    }
}