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
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create Account", fontSize = 28.sp, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") }, modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { if (username.isNotEmpty() && email.isNotEmpty() && password == confirmPassword) navController.navigate("login") }, modifier = Modifier.fillMaxWidth(0.5f), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.clickable { navController.navigate("login") }) {
                Text(text = "Already a member?")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Sign In", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
