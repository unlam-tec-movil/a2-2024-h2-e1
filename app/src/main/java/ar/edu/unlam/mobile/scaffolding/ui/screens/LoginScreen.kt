package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel,
) {
    // La información que obtenemos desde el view model la consumimos a través de un estado de
    // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
    // un estado de éxito y un mensaje de error.

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoginForm(navController, viewModel)
        }
    }
}

@Composable
fun LoginForm(
    navController: NavHostController,
    viewModel: LoginViewModel,
) {
    val email by viewModel.email
    val password by viewModel.password

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val gradientColors = listOf(Cyan, Blue, Magenta)

        Text(
            text = "Ingresar",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.width(200.dp),
            style =
                TextStyle(
                    brush =
                        Brush.linearGradient(
                            colors = gradientColors,
                        ),
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = viewModel::setEmail,
            label = { Text("Email") },
        )

        OutlinedTextField(
            value = password,
            onValueChange = viewModel::setPassword,
            label = { Text("Password") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { viewModel.logIn() },
        ) {
            Text("Log In")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Surface(onClick = { navController.navigate("Register") }) {
            Column {
                Text(
                    text = "¿No tienes una cuenta?",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.width(200.dp),
                    style =
                    TextStyle(
                        brush =
                        Brush.linearGradient(
                            colors = gradientColors,
                        ),
                    ),
                )
                Text(
                    text = "Registrate",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    modifier = Modifier.width(200.dp),
                    style =
                    TextStyle(
                        brush =
                        Brush.linearGradient(
                            colors = gradientColors,
                        ),
                    ),
                )
            }
        }
    }
}
