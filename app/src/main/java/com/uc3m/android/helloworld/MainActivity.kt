package com.uc3m.android.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc3m.android.helloworld.ui.theme.HelloWorldTheme
import com.uc3m.android.helloworld.screens.LoginScreen
import com.uc3m.android.helloworld.screens.SignUpScreen
import com.uc3m.android.helloworld.screens.HomeScreen
import com.uc3m.android.helloworld.screens.MotivationStrategyScreen
import com.uc3m.android.helloworld.screens.OfflineModeScreen
import com.uc3m.android.helloworld.screens.PrepExams
import com.uc3m.android.helloworld.screens.ProjectsPreparationScreen
import com.uc3m.android.helloworld.screens.StudyProgressScreen
import com.uc3m.android.helloworld.screens.SubjectScreen
import com.uc3m.android.helloworld.screens.StudyPlansScreen
import com.uc3m.android.helloworld.screens.ReviewClarificationScreen
import com.uc3m.android.helloworld.screens.TimeManagementScreen
import com.uc3m.android.helloworld.screens.WorkingInTeamsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("signup") { SignUpScreen(navController) }
                    composable("subjects") { SubjectScreen(navController) }
                    composable("study_plans") { StudyPlansScreen(navController) }
                    composable("study_progress") { StudyProgressScreen(navController) }
                    composable("offline_mode") { OfflineModeScreen(navController)}
                    composable("offline_mode/{subject}") { backStackEntry ->
                        val subject = backStackEntry.arguments?.getString("subject") ?: return@composable
                        OfflineModeScreen(navController)
                    }
                    composable("study_plans") { StudyPlansScreen(navController) }
                    composable("exams_preparation") { PrepExams(navController) }
                    composable("projects_preparation") { ProjectsPreparationScreen(navController) }
                    composable("review_clarification") { ReviewClarificationScreen(navController) }
                    composable("motivation_strategy") { MotivationStrategyScreen(navController) }
                    composable("working_in_teams") { WorkingInTeamsScreen(navController) }
                    composable("time_management") { TimeManagementScreen(navController) }
                }
            }
        }
    }
}
