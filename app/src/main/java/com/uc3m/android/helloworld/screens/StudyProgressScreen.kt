package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyProgressScreen(navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }
    val naranjitafondo = Color(0xFFFF9966) // Naranja claro
    val blackColor = Color(0xFF000000)
    val whiteColor = Color(0xFFFFFFFF)
    val orangeColor = Color(0xFFfc4b08) // Naranja del tono que mencionaste

    // Progreso predefinido para cada asignatura
    val subjects = listOf(
        "Maths" to 0.7f,
        "Geography" to 0.5f,
        "History" to 0.3f,
        "English" to 0.8f,
        "Spanish" to 0.6f,
        "Music" to 0.4f,
        "Biology" to 0.9f,
        "Physics" to 0.2f,
        "Art" to 0.75f
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Study Progress",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = whiteColor
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = naranjitafondo,
                    titleContentColor = whiteColor
                ),
                actions = {
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options",
                                tint = blackColor
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Grades") },
                                onClick = { showMenu = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Statistics") },
                                onClick = { showMenu = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Back to Home") },
                                onClick = {
                                    showMenu = false
                                    navController.navigate("home") {
                                        popUpTo("study_progress") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFF5F5F5))
            ) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    contentColor = blackColor,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    NavigationBarItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(48.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFAAAAAA), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "📚",
                                        fontSize = 20.sp,
                                        color = naranjitafondo,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate("subjects") },
                        label = null,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(48.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFAAAAAA), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "📅",
                                        fontSize = 20.sp,
                                        color = naranjitafondo,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate("study_plans") },
                        label = null,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(48.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFAAAAAA), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "Home",
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate("home") },
                        label = null,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(96.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(96.dp)
                                        .background(naranjitafondo, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "📊",
                                        fontSize = 24.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        },
                        selected = true,
                        onClick = { /* Already on study progress */ },
                        label = null,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(48.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFAAAAAA), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "💾",
                                        fontSize = 20.sp,
                                        color = naranjitafondo,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        },
                        selected = false,
                        onClick = { navController.navigate("offline_mode") },
                        label = null,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(whiteColor) // Fondo blanco
                .padding(paddingValues),
            color = whiteColor
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(40.dp)) // Espacio de 40dp desde la parte superior

                // LazyColumn para el desplazamiento
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
                ) {
                    items(subjects) { (subject, progress) ->
                        ProgressItem(subject = subject, progress = progress, orangeColor = orangeColor)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressItem(subject: String, progress: Float, orangeColor: Color) {
    // Contenedor de cada asignatura con el progreso
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF9966)) // Celdas naranjitas
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Columna con el nombre y el progreso
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = subject,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = orangeColor // Nombre de la asignatura en naranja
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = orangeColor // Barra de progreso en naranja
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${(progress * 100).toInt()}% Completed",
                    fontSize = 14.sp,
                    color = Color.Black // Texto de porcentaje en negro
                )
            }
        }
    }
}
