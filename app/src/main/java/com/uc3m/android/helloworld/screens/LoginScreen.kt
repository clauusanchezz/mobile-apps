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
import androidx.compose.ui.graphics.Color
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
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.font.FontWeight

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
    //0xFFFF9966
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFF9966)) { // Naranja de fondo
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {
            // Imagen ocupa la mitad superior de la pantalla
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.31f) // La imagen ocupa la mitad superior de la pantalla
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter) // Alinea la imagen al centro superior
                )
            }
            Text(
                text = "",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold, // Título en negrita
                color = Color.White, // Texto blanco
                style = MaterialTheme.typography.headlineLarge, // Estilo de encabezado
                modifier = Modifier.padding(top = 30.dp) // Espacio entre la imagen y el título
            )
            // Campos de entrada de usuario y contraseña
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp), // Padding horizontal
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp), // Padding horizontal
                shape = RoundedCornerShape(10.dp)
            )

            // Botón de inicio de sesión
            Button(
                onClick = { validateLogin() },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 20.dp), // Añadido padding vertical para espacio
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Sign In", fontSize = 18.sp, color = Color.White)
            }

            // Enlace para registro
        )
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

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado uniforme
                verticalAlignment = Alignment.CenterVertically // Alineación vertical centrada
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spain_flag),
                    contentDescription = "Spanish Flag",
                    modifier = Modifier.size(40.dp) // Asegura que ambas banderas tengan el mismo tamaño
                )

                Image(
                    painter = painterResource(id = R.drawable.uk_flag),
                    contentDescription = "UK Flag",
                    modifier = Modifier.size(40.dp) // Tamaño idéntico a la otra bandera
                )
            }

        }
    }
}