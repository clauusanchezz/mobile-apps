package com.uc3m.android.helloworld.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uc3m.android.helloworld.data.Question
import com.uc3m.android.helloworld.viewmodel.DataBaseViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uc3m.android.helloworld.data.QuestionType
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.foundation.Canvas



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    unitId: String,
    subjectId: String,
    navController: NavController,
    viewModel: DataBaseViewModel = viewModel()
) {
    val orange = Color(0xFFFF9966)
    val white = Color.White
    val green = Color(0xFF4CAF50)
    val red = Color(0xFFF44336)

    // Observar las preguntas desde el ViewModel
    val questions by viewModel.questions.observeAsState(emptyList())
    val currentQuestionIndex = remember { mutableStateOf(0) }
    val currentQuestion = questions.getOrNull(currentQuestionIndex.value)
    val selectedAnswer = remember { mutableStateOf<String?>("") }
    val isAnswerSelected = remember { mutableStateOf(false) }

    // Cargar las preguntas al inicio
    LaunchedEffect(Unit) {
        viewModel.loadQuestionsForUnit(subjectId, unitId)  // Cargar preguntas de la unidad
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Questions",
                        fontSize = 28.sp,
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = orange
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = white
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                currentQuestion?.let { question ->
                    // Título de la pregunta
                    Text(
                        text = question.questionText,
                        style = TextStyle(fontSize = 20.sp, color = Color.Black),
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    when (question.type) {
                        QuestionType.MULTIPLE_CHOICE -> MultipleChoiceQuestion(
                            question = question,
                            selectedAnswer = selectedAnswer,
                            isAnswerSelected = isAnswerSelected
                        )
                        QuestionType.TRUE_FALSE -> TrueFalseQuestion(
                            question = question,
                            selectedAnswer = selectedAnswer,
                            isAnswerSelected = isAnswerSelected
                        )
                        QuestionType.SHORT_ANSWER -> ShortAnswerQuestion(
                            question = question,
                            selectedAnswer = selectedAnswer,
                            isAnswerSelected = isAnswerSelected
                        )
                        QuestionType.MATCHING -> MatchingQuestion(
                            question = question,
                            selectedAnswer = selectedAnswer,
                            isAnswerSelected = isAnswerSelected
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botón para avanzar a la siguiente pregunta
                    if (currentQuestionIndex.value < questions.size - 1) {
                        Button(
                            onClick = {
                                if (isAnswerSelected.value) {
                                    currentQuestionIndex.value++
                                    isAnswerSelected.value = false
                                    selectedAnswer.value = ""
                                } else {
                                    Toast.makeText(
                                        navController.context,
                                        "Please select an answer",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange
                            )
                        ) {
                            Text("Next Question")
                        }
                    } else {
                        Button(
                            onClick = {
                                navController.navigate("results")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange
                            )
                        ) {
                            Text("View Results")
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
    selectedAnswer: MutableState<String?>,
    isAnswerSelected: MutableState<Boolean>
) {
    question.options?.forEachIndexed { index, option ->
        val buttonColor = when {
            selectedAnswer.value == option -> if (option == question.correctAnswer) Color.Green else Color.Red
            else -> Color.White
        }

        Button(
            onClick = {
                selectedAnswer.value = option
                isAnswerSelected.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text(text = option, color = Color.Black)
        }
    }
}

@Composable
fun TrueFalseQuestion(
    question: Question,
    selectedAnswer: MutableState<String?>,
    isAnswerSelected: MutableState<Boolean>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                selectedAnswer.value = "True"
                isAnswerSelected.value = true
            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedAnswer.value == "True") Color.Green else Color.White
            )
        ) {
            Text("True")
        }
        Button(
            onClick = {
                selectedAnswer.value = "False"
                isAnswerSelected.value = true
            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedAnswer.value == "False") Color.Green else Color.White
            )
        ) {
            Text("False")
        }
    }
}

@Composable
fun ShortAnswerQuestion(
    question: Question,
    selectedAnswer: MutableState<String?>,
    isAnswerSelected: MutableState<Boolean>
) {
    BasicTextField(
        value = selectedAnswer.value.orEmpty(),
        onValueChange = {
            selectedAnswer.value = it
            isAnswerSelected.value = it.isNotEmpty()
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
    )
}

@Composable
fun MatchingQuestion(
    question: Question,
    selectedAnswer: MutableState<String?>,
    isAnswerSelected: MutableState<Boolean>
) {
    val leftOptions = question.leftColumnOptions ?: emptyList() // Elementos a la izquierda
    val rightOptions = question.rightColumnOptions ?: emptyList() // Elementos a la derecha

    val matchings = remember { mutableStateOf(mutableMapOf<String, String>()) }
    val leftPositions = remember { mutableStateOf<MutableList<Offset>>(mutableListOf()) }
    val rightPositions = remember { mutableStateOf<MutableList<Offset>>(mutableListOf()) }


    // Column layout for matching question
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            leftOptions.forEachIndexed { index, leftOption ->
                Button(
                    onClick = {
                        matchings.value[leftOption] = rightOptions[index] // Emparejar
                        isAnswerSelected.value = true
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .onGloballyPositioned { coordinates ->
                            // Guardar las posiciones de los elementos de la izquierda
                            val position = coordinates.positionInRoot()
                            leftPositions.value.add(position)
                        },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(text = leftOption, color = Color.Black)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            rightOptions.forEachIndexed { index, rightOption ->
                Button(
                    onClick = {
                        matchings.value[rightOption] = leftOptions[index] // Emparejar
                        isAnswerSelected.value = true
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .onGloballyPositioned { coordinates ->
                            // Guardar las posiciones de los elementos de la derecha
                            val position = coordinates.positionInRoot()
                            rightPositions.value.add(position)
                        },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(text = rightOption, color = Color.Black)
                }
            }
        }

        // Dibujar las líneas de emparejamiento
        Canvas(modifier = Modifier.fillMaxSize()) {
            matchings.value.forEach { (leftOption, rightOption) ->
                val leftIndex = leftOptions.indexOf(leftOption)
                val rightIndex = rightOptions.indexOf(rightOption)

                // Obtener las posiciones de los elementos emparejados
                val leftPos = leftPositions.value.getOrNull(leftIndex) ?: Offset(0f, 0f)
                val rightPos = rightPositions.value.getOrNull(rightIndex) ?: Offset(0f, 0f)

                // Dibujar una línea entre los elementos emparejados
                drawLine(
                    color = Color.Blue, // Color de la línea
                    start = leftPos.copy(y = leftPos.y + 50), // Ajusta según la altura de los botones
                    end = rightPos.copy(y = rightPos.y + 50), // Ajusta según la altura de los botones
                    strokeWidth = 4f
                )
            }
        }

        // Muestra los emparejamientos
        Spacer(modifier = Modifier.height(16.dp))
        Text("Matched pairs:")
        matchings.value.forEach { (left, right) ->
            Text("$left -> $right")
        }
    }
}

