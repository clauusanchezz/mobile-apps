package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uc3m.android.helloworld.R

@Composable
fun HomeScreen(navController: NavController) {
    val orangeColor = Color(0xFFfc4b08) // Naranja para los botones
    val whiteColor = Color(0xFFFFFFFF) // Blanco para el fondo
    val blackColor = Color(0xFF000000) // Negro para el título
    val naranjitafondo = Color(0xFFFF9966)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(naranjitafondo)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Esto coloca los elementos en el centro de la pantalla
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(250.dp) // Aumenta el tamaño de la imagen
                    .padding(top = 32.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Welcome to StudyStorm!",
                fontSize = 28.sp,
                color = blackColor,
                modifier = Modifier.padding(top = 12.dp) // Se añade un poco de espacio arriba del título
            )
            Text(
                text = "Select an option:",
                fontSize = 18.sp,
                color = blackColor,
                modifier = Modifier.padding(top = 12.dp) // Se añade un poco de espacio arriba del título
            )

            // Cuadrícula de botones
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Fila 1 (primeros dos botones)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { navController.navigate("subjects") },
                            modifier = Modifier
                                .size(160.dp) // Tamaño grande del botón
                                .padding(16.dp),
                            shape = RoundedCornerShape(60.dp), // Botón circular
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeColor,
                                contentColor = whiteColor
                            )
                        ) {
                            Text("Subjects", fontSize = 16.sp, textAlign = TextAlign.Center)
                        }

                        Spacer(modifier = Modifier.width(32.dp)) // Espaciado entre los botones

                        Button(
                            onClick = { navController.navigate("study_plans") },
                            modifier = Modifier
                                .size(160.dp) // Tamaño grande del botón
                                .padding(16.dp),
                            shape = RoundedCornerShape(60.dp), // Botón circular
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeColor,
                                contentColor = whiteColor
                            )
                        ) {
                            Text("Study Plans", fontSize = 16.sp, textAlign = TextAlign.Center)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp)) // Espaciado entre filas

                    // Fila 2 (segundos dos botones)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { navController.navigate("study_progress") },
                            modifier = Modifier
                                .size(160.dp) // Tamaño grande del botón
                                .padding(16.dp),
                            shape = RoundedCornerShape(60.dp), // Botón circular
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeColor,
                                contentColor = whiteColor
                            )
                        ) {
                            Text("Progress", fontSize = 16.sp, textAlign = TextAlign.Center)
                        }

                        Spacer(modifier = Modifier.width(32.dp)) // Espaciado entre los botones

                        Button(
                            onClick = { navController.navigate("offline_mode") },
                            modifier = Modifier
                                .size(160.dp) // Tamaño grande del botón
                                .padding(16.dp),
                            shape = RoundedCornerShape(60.dp), // Botón circular
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeColor,
                                contentColor = whiteColor
                            )
                        ) {
                            Text("Offline", fontSize = 16.sp, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}
