package com.uc3m.android.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc3m.android.helloworld.ui.theme.HelloWorldTheme
import com.uc3m.android.helloworld.screens.*
import com.uc3m.android.helloworld.viewmodel.*
import kotlinx.coroutines.launch
import androidx.compose.runtime.livedata.observeAsState



class MainActivity : ComponentActivity() {

    private lateinit var dbViewModel: DataBaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create and observe ViewModel
        dbViewModel = ViewModelProvider(this)[DataBaseViewModel::class.java]

        lifecycleScope.launch {
            // TODO NO FUNCIONA
            //dbViewModel.resetInitializationFlag()
            // Initialize the database if not initialized
            if (dbViewModel.isInitialized.value != true) {
            dbViewModel.initializeDatabase() // Initialize database if not initialized
            } else {
                //dbViewModel.startListeningToSubjects() // Start listening to subjects if initialized
            }
        }

        setContent {
            HelloWorldTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("signup") { SignUpScreen(navController) }
                    composable("subjects") {
                        // Observe subjects from the ViewModel
                        val subjects = dbViewModel.subjects.observeAsState(emptyList()).value
                        SubjectScreen(navController, dbViewModel)
                    }
                    composable("study_plans") { StudyPlansScreen(navController) }
                    composable("study_progress") { StudyProgressScreen(navController) }
                    composable("offline_mode") { OfflineModeScreen(navController) }
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
                    composable("educational_facts") { EducationalFactsScreen(navController) }
                    composable("profile_settings") { ProfileSettingsScreen(navController) }

                    // Route for navigating to UnitScreen
                    composable("units/{subjectName}") { backStackEntry ->
                        val subjectName = backStackEntry.arguments?.getString("subjectName") ?: return@composable
                        UnitScreen(subjectName = subjectName, navController = navController)
                    }

                    // Route for navigating to QuestionsScreen
                    composable("questions_screen/{subjectId}/{unitId}") { backStackEntry ->
                        val subjectId = backStackEntry.arguments?.getString("subjectId") ?: return@composable
                        val unitId = backStackEntry.arguments?.getString("unitId") ?: return@composable
                        QuestionsScreen(subjectId = subjectId, unitId = unitId, navController = navController)
                    }

                    // Route for navigating to ResultsScreen
                    composable("results/{score}/{subjectName}/{correctAnswers}/{totalQuestions}") { backStackEntry ->
                        val score = backStackEntry.arguments?.getString("score")?.toFloatOrNull() ?: 0f
                        val subjectName = backStackEntry.arguments?.getString("subjectName") ?: ""
                        val correctAnswers = backStackEntry.arguments?.getString("correctAnswers")?.toIntOrNull() ?: 0
                        val totalQuestions = backStackEntry.arguments?.getString("totalQuestions")?.toIntOrNull() ?: 0
                        ResultsScreen(
                            score = score,
                            subjectName = subjectName,
                            correctAnswers = correctAnswers,
                            totalQuestions = totalQuestions,
                            navController = navController
                        )
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop listening to subjects and units when the activity is destroyed
        dbViewModel.stopListening()
    }
}
