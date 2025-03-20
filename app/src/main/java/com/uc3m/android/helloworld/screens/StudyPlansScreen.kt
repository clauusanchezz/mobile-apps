package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StudyPlansScreen(navController: NavController) {
    val whiteColor = Color(0xFFFFFFFF)
    // Lista de planes de estudio predefinidos
    val studyPlans = listOf(
        "My personalized Study Plan" to "personalized_plan", // Asociar nombre y destino
        "Preparation of Exams" to "exams_preparation",
        "Projects' Preparation" to "projects_preparation",
        "Review and clarification" to "review_clarification",
        "Motivation Strategy" to "motivation_strategy",
        "Working in Teams" to "working_in_teams",
        "Time Management" to "time_management"
    )

    // Contenedor principal
    Surface(modifier = Modifier.fillMaxSize().background(whiteColor).padding(16.dp), color = whiteColor) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la pantalla
            Text(
                text = "Study Plans & Tips",
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // LazyColumn para los planes de estudio
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(studyPlans) { (plan, destination) -> // Destino de la pantalla como segundo valor
                    StudyPlanItem(plan = plan, onClick = { navController.navigate(destination) })
                    Spacer(modifier = Modifier.height(16.dp)) // Separador entre planes
                }
            }
        }
    }
}

@Composable
fun StudyPlanItem(plan: String, onClick: () -> Unit) {
    val naranjitafondo = Color(0xFFFF9966)
    // Contenedor para cada plan de estudio, con navegación al hacer clic
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Maneja la acción de clic
        colors = CardDefaults.cardColors(containerColor = naranjitafondo)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nombre del plan de estudio
            Text(
                text = plan,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}
