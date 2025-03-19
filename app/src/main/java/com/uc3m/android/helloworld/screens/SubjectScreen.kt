package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun SubjectScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF7F7F7)) { // Light background color
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp), // Padding on the sides to avoid edges touching the screen
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title with larger font size and bold for more emphasis
            Text(
                text = "Subjects",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000), // Orange color for the title
            )
            Spacer(modifier = Modifier.height(20.dp))

            // LazyColumn for dynamic and smooth scrolling when the list is long
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp), // Space between each item
                contentPadding = PaddingValues(top = 12.dp) // Padding from top
            ) {
                val subjects = listOf("Maths", "Geography", "History", "English", "Spanish", "Music", "Biology", "Physics", "Art")

                items(subjects.size) { index ->
                    SubjectButton(subjectName = subjects[index]) {
                        // Handle navigation or click event
                        // navController.navigate("destination_screen")
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectButton(subjectName: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp) // Bigger height for buttons
            .padding(vertical = 8.dp), // Vertical padding between buttons
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF6F00), // Orange button color
            contentColor = Color.White // Black text color for buttons
        ),
        shape = RoundedCornerShape(16.dp), // Rounded corners for buttons
    ) {
        Text(text = subjectName, fontSize = 20.sp, fontWeight = FontWeight.Medium)
    }
}


