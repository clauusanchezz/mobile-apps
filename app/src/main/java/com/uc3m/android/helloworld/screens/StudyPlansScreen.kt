package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.background
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
    // Lista de planes de estudio predefinidos
    val studyPlans = listOf(
        "My personalized Study Plan",
        "Preparation of Exams",
        "Projects' Preparation",
        "Review and clarification",
        "Motivation Strategy",
        "Working in Teams",
        "Time Management"
    )

    // Contenedor principal
    Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
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
                items(studyPlans) { plan ->
                    StudyPlanItem(plan = plan)
                    Spacer(modifier = Modifier.height(16.dp)) // Separador entre planes
                }
            }
        }
    }
}

@Composable
fun StudyPlanItem(plan: String) {
    // Contenedor para cada plan de estudio
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                color = Color.Black
            )
        }
    }
}
