package com.uc3m.android.helloworld.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
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
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.font.FontWeight

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
