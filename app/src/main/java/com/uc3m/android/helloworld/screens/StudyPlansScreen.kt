package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlansScreen(navController: NavController?) {
    val whiteColor = Color(0xFFFFFFFF)
    val titleColor = Color(0xFFFF9966) // Naranja claro
    val naranjitafondo = Color(0xFFFF9966) // Fondo naranja para el topBar
    val blackColor = Color(0xFF000000) // Color negro para el texto del título

    val studyPlans = listOf(
        "📚 Exams" to "exams_preparation",
        "📝 Projects" to "projects_preparation",
        "🔍 Review" to "review_clarification",
        "💡 Motivation" to "motivation_strategy",
        "🤝 Teams" to "working_in_teams",
        "⏳ Time" to "time_management"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Study Plans & Tips",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = whiteColor // Título blanco
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = naranjitafondo, // Fondo naranja
                    titleContentColor = whiteColor // Título blanco
                ),
            )
        }
    ) { paddingValues ->
        // Contenido de la pantalla debajo del TopAppBar
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = whiteColor // Fondo blanco
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Lista en zigzag con círculos grandes y más juntos
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy((-30).dp), // Se solapan un poco
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    itemsIndexed(studyPlans) { index, (plan, destination) ->
                        StudyPlanItem(
                            plan = plan,
                            onClick = { navController?.navigate(destination) },
                            alignLeft = index % 2 == 0 // Alterna la alineación
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudyPlanItem(plan: String, onClick: () -> Unit, alignLeft: Boolean) {
    val buttonColor = Color(0xFFFF9966) // Naranja claro

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (alignLeft) Arrangement.Start else Arrangement.End // Zigzag
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(190.dp), // Círculos más grandes
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = CircleShape // Botón completamente circular
        ) {
            Text(
                text = plan,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
