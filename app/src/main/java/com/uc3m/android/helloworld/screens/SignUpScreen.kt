package com.uc3m.android.helloworld.screens

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast

@Composable
fun SignUpScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Get the context using LocalContext.current
    val context = LocalContext.current

    // SharedPreferences to store user data imputed during sign up
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    // Save user data to SharedPreferences
    fun saveUserData(username: String, email: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()  // Save data
    }

    // Check if the username already exists
    fun checkUsernameExists(username: String): Boolean {
        val savedUsername = sharedPreferences.getString("username", "")
        return savedUsername == username  // Check if the entered username matches the saved one
    }

    // Check if the email already exists
    fun checkEmailExists(email: String): Boolean {
        val savedEmail = sharedPreferences.getString("email", "")
        return savedEmail == email  // Check if the entered email matches the saved one
    }

    // Handle the sign-up logic
    fun handleSignUp() {
        // Check if any of the fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            // Show error if any field is empty
            Toast.makeText(context, "All fields with (*) must be filled", Toast.LENGTH_SHORT).show()
            return  // Stop the function execution
        }
        if (checkEmailExists(email)) {
            // Check if the email already exists first
            Toast.makeText(context, "Email already used", Toast.LENGTH_SHORT).show()
        } else if (checkUsernameExists(username)) {
            // If the email is available, check if the username is already taken
            Toast.makeText(context, "Username already used", Toast.LENGTH_SHORT).show()
        } else if (password != confirmPassword) {
            Toast.makeText(context, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
        } else {
            // If both the email and username are available, save the data
            saveUserData(username, email, password)
            navController.navigate("login")
        }
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create Account", fontSize = 28.sp, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username*") }, modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email*") }, modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password*") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password*") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(10.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { handleSignUp()}, modifier = Modifier.fillMaxWidth(0.5f), shape = RoundedCornerShape(10.dp)) {
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