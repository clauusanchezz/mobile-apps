package com.uc3m.android.helloworld.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import com.uc3m.android.helloworld.R
import android.widget.Toast

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Get the context using LocalContext.current
    val context = LocalContext.current

    // SharedPreferences to retrieve user data
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedUsername = sharedPreferences.getString("username", "")
    val savedPassword = sharedPreferences.getString("password", "")

    fun validateLogin() {
        if (username == savedUsername && password == savedPassword) {
            navController.navigate("home")
        } else {
            // Show an error message
            Toast.makeText(context, "Incorrect username or password!", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo", modifier = Modifier.size(120.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "STUDYSTORM", fontSize = 28.sp, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") }, modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { validateLogin() }, modifier = Modifier.fillMaxWidth(0.5f), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Sign In")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.clickable { navController.navigate("signup") }) {
                Text(text = "Not a member?")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Sign up", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
