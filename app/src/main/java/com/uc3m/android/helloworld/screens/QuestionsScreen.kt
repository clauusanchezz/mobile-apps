package com.uc3m.android.helloworld.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uc3m.android.helloworld.data.Question
import com.uc3m.android.helloworld.viewmodel.DataBaseViewModel
import com.uc3m.android.helloworld.screens.MapQuizScreen
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uc3m.android.helloworld.data.QuestionType
import androidx.compose.foundation.clickable
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.itemsIndexed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    unitId: String,
    subjectId: String,
    navController: NavController,
    viewModel: DataBaseViewModel = viewModel()
) {
    // theme colors
    val orange = Color(0xFFFF9966)
    val white  = Color.White
    val lightBg = Color(0xFFFFE5D9)

    // load exactly five random questions once
    val questions by viewModel.randomQuestions.observeAsState(emptyList())
    var currentIndex by remember { mutableStateOf(0) }
    val answers = remember { mutableStateMapOf<String, String>() }
    val anyAnswered by remember { derivedStateOf { answers.isNotEmpty() } }

    LaunchedEffect(subjectId, unitId) {
        viewModel.loadRandomQuestions(subjectId, unitId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Practice Questions", fontSize = 28.sp, color = white) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = orange)
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = lightBg
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(questions.take(currentIndex + 1)) { idx, question ->
                    if (question.type == QuestionType.MAP) {
                        // Map question
                        MapQuizScreen(
                            question    = question,
                            onRegionTapped = { tappedName ->
                                // record answer
                                question.id?.let { answers[it] = tappedName }
                                // advance or finish
                                if (idx < questions.lastIndex) {
                                    currentIndex = idx + 1
                                } else {
                                    finishQuiz(questions, answers, subjectId, navController, viewModel)
                                }
                            }
                        )
                    } else {
                        // regular question card
                        QuestionCard(
                            question           = question,
                            questionNumber     = idx + 1,
                            userAnswers        = answers,
                            onAnswerSelected   = { qid, answer ->
                                answers[qid] = answer
                                if (idx < questions.lastIndex) {
                                    currentIndex = idx + 1
                                }
                            }
                        )
                    }
                }

                // final “View Results” button for non-map last question
                item {
                    if (currentIndex == questions.lastIndex && anyAnswered) {
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = {
                                finishQuiz(questions, answers, subjectId, navController, viewModel)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = orange)
                        ) {
                            Text("View Results", color = white)
                        }
                    }
                }
            }
        }
    }
}

private fun finishQuiz(
    questions: List<Question>,
    answers: Map<String,String>,
    subjectId: String,
    navController: NavController,
    viewModel: DataBaseViewModel
) {
    // compute percent score
    val correct = answers.count { (qid, ans) ->
        questions.find { it.id == qid }?.correctAnswer.toString() == ans
    }
    val pct = (correct.toFloat() / questions.size * 100).toInt()
    viewModel.markTestAsCompleted(subjectId)
    navController.navigate("results/$pct/$subjectId/$correct/${questions.size}")
}

@Composable
fun QuestionCard(
    question: Question,
    questionNumber: Int,
    userAnswers: Map<String, String>,
    onAnswerSelected: (String, String) -> Unit
) {
    val userAnswer = question.id?.let { userAnswers[it] }
    val isAnswered = userAnswer != null
    val isCorrect = when (question.type) {
        QuestionType.SHORT_ANSWER, QuestionType.TRUE_FALSE ->
            userAnswer?.equals(question.correctAnswer.toString(), ignoreCase = true) == true
        else ->
            userAnswer == question.correctAnswer
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF5EC))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Question $questionNumber",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium))
            Spacer(Modifier.height(8.dp))
            Text(question.questionText,
                style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(16.dp))

            when (question.type) {
                QuestionType.MULTIPLE_CHOICE -> MultipleChoiceQuestion(
                    question        = question,
                    userAnswer      = userAnswer,
                    isAnswered      = isAnswered,
                    onAnswerSelected = { onAnswerSelected(question.id!!, it) }
                )
                QuestionType.TRUE_FALSE -> TrueFalseQuestion(
                    question        = question,
                    userAnswer      = userAnswer,
                    isAnswered      = isAnswered,
                    onAnswerSelected = { onAnswerSelected(question.id!!, it) }
                )
                QuestionType.SHORT_ANSWER -> ShortAnswerQuestion(
                    question        = question,
                    userAnswer      = userAnswer,
                    isAnswered      = isAnswered,
                    onAnswerSelected = { onAnswerSelected(question.id!!, it) }
                )
                QuestionType.MATCHING -> MatchingQuestion(
                    question        = question,
                    userAnswer      = userAnswer,
                    isAnswered      = isAnswered,
                    onAnswerSelected = { onAnswerSelected(question.id!!, it) }
                )
                else -> { /* MAP is handled above */ }
            }

            if (isAnswered) {
                Spacer(Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (isCorrect) "Correct!" else "Incorrect",
                            fontWeight = FontWeight.Bold,
                            color = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                        )
                        if (!isCorrect) {
                            Spacer(Modifier.height(8.dp))
                            Text("Answer: ${question.correctAnswer}",
                                color = Color(0xFF2E7D32))
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun MultipleChoiceQuestion(
    question: Question,
    userAnswer: String?,
    isAnswered: Boolean,
    onAnswerSelected: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        question.options?.forEach { option ->
            val isSelected = userAnswer == option
            val isCorrect = option == question.correctAnswer && isAnswered

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (!isAnswered) {
                            onAnswerSelected(option)
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        isCorrect -> Color(0xFFE8F5E9)
                        isSelected -> Color(0xFFFFEBEE)
                        else -> Color.White
                    }
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = option,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = when {
                        isCorrect -> Color(0xFF2E7D32)
                        isSelected -> Color(0xFFC62828)
                        else -> Color.Black
                    }
                )
            }
        }
    }
}

@Composable
fun TrueFalseQuestion(
    question: Question,
    userAnswer: String?,
    isAnswered: Boolean,
    onAnswerSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        listOf("True", "False").forEach { option ->
            val isSelected = userAnswer?.toString()?.toLowerCase() == option.toLowerCase()
            val isCorrect = option.toLowerCase() == question.correctAnswer?.toString()?.toLowerCase() && isAnswered

            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (!isAnswered) {
                            onAnswerSelected(option)
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        isCorrect -> Color(0xFFE8F5E9)
                        isSelected -> Color(0xFFFFEBEE)
                        else -> Color.White
                    }
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = option,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = when {
                        isCorrect -> Color(0xFF2E7D32)
                        isSelected -> Color(0xFFC62828)
                        else -> Color.Black
                    }
                )
            }
        }
    }
}

@Composable
fun ShortAnswerQuestion(
    question: Question,
    userAnswer: String?,
    isAnswered: Boolean,
    onAnswerSelected: (String) -> Unit
) {
    var text by remember { mutableStateOf(userAnswer ?: "") }
    var isSubmitting by remember { mutableStateOf(false) }

    Column {
        BasicTextField(
            value = text,
            onValueChange = {
                if (!isAnswered && !isSubmitting) {
                    text = it
                }
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        if (!isAnswered && text.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    isSubmitting = true
                    onAnswerSelected(text)
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9966)
                )
            ) {
                Text("Submit Answer")
            }
        }
    }
}

@Composable
fun MatchingQuestion(
    question: Question,
    userAnswer: String?,
    isAnswered: Boolean,
    onAnswerSelected: (String) -> Unit
) {
    // Implementation for matching questions
    Text("Matching question type not implemented yet")
}

