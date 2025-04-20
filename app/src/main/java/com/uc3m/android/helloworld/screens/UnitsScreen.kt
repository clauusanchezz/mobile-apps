package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uc3m.android.helloworld.data.SubjectUnit
import com.uc3m.android.helloworld.viewmodel.DataBaseViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitScreen(
    subjectName: String,
    navController: NavController,
    viewModel: DataBaseViewModel = viewModel()
) {
    val orange = Color(0xFFFF9966)
    val white = Color.White

    // Observar las unidades desde el ViewModel
    val units by viewModel.units.observeAsState(emptyList())

    // Cargar las unidades al inicio
    LaunchedEffect(Unit) {
        viewModel.loadUnitsForSubject(subjectName) // Función para cargar unidades según el nombre de la asignatura
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = subjectName,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {  // Regresar a la pantalla anterior
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = white
                        )
                    }
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
                // Listado de unidades
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
                ) {
                    items(units.size) { index ->
                        val unit = units[index]
                        UnitButton(unitName = unit.name) {
                            navController.navigate("questions_screen/${subjectName}/${unit.id}")
// Navegar a la pantalla de detalles de la unidad
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UnitButton(unitName: String, onClick: () -> Unit) {
    val normalColor = Color(0xFFFF9966)
    val pressedColor = Color(0xFFE07B4F)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) pressedColor else normalColor

    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
    ) {
        Text(
            text = unitName,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
    }
}
