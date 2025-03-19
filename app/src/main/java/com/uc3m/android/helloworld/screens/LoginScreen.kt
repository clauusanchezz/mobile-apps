package com.uc3m.android.helloworld.screens

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
import com.uc3m.android.helloworld.auth.FirebaseAuthHelper
import android.widget.Toast
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val authHelper = remember { FirebaseAuthHelper() }

    fun handleLogin() {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        scope.launch {
            isLoading = true
            try {
                val result = authHelper.signIn(email, password)
                result.fold(
                    onSuccess = { user ->
                        val username = authHelper.getCurrentUsername()
                        Toast.makeText(context, "Welcome back, $username!", Toast.LENGTH_SHORT).show()
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onFailure = { exception ->
                        Toast.makeText(context, "Login failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                )
            } finally {
                isLoading = false
            }
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
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { handleLogin() },
                modifier = Modifier.fillMaxWidth(0.5f),
                shape = RoundedCornerShape(10.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(text = "Sign In")
                }
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