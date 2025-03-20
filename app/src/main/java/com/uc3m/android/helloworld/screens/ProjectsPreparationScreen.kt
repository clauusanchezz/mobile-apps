package com.uc3m.android.helloworld.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProjectsPreparationScreen(navController: NavController) {
    val titleColor = Color(0xFFFF9966) // Naranja claro
    // List of tips with emojis
    val tips2 = listOf(
        "📅 Set clear goals: Define your project's objectives and milestones",
        "📝 Plan your tasks: Break your project into smaller, manageable tasks and set deadlines",
        "💡 Brainstorm ideas: Think creatively and explore different approaches before starting",
        "📖 Research thoroughly: Gather all the necessary information and resources",
        "📚 Document your progress: Keep track of your work with regular updates and notes",
        "🤔 Review your work: Evaluate the project's progress and make adjustments as needed",
        "🥤 Stay hydrated and energized: Drink water, eat healthy, and take care of yourself to stay productive",
        "🛌 Rest well: Ensure you get enough rest so that you can be focused when working on your project"
    )

    // Main container for the screen
    Surface(modifier = Modifier.fillMaxSize().background(Color(0xFFFFFFFF)).padding(16.dp), color = Color(0xFFFFFFFF)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            // Title of the screen
            Text(
                text = "Preparation of Projects",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Title of the tips list
            Text(
                text = "Useful Tips",
                fontSize = 24.sp,
                color = titleColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // List of tips
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(tips2) { tip2 ->
                    TipItem2(tip2)
                    Spacer(modifier = Modifier.height(12.dp)) // Space between tips
                }
            }

            // Button to go back to the study plans screen
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back to Study Plans")
            }
        }
    }
}

@Composable
fun TipItem2(tip2: String) {
    // Container for each tip
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF9966)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tip2,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

