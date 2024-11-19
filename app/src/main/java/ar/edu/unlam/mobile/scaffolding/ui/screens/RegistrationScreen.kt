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
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: LoginViewModel,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        RegistrationForm(navController, viewModel)
    }
}

@Composable
fun RegistrationForm(
    navController: NavHostController,
    viewModel: LoginViewModel,
) {
    val email by viewModel.email
    val name by viewModel.name
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
            text = "Registrarse",
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
            value = name,
            onValueChange = viewModel::setName,
            label = { Text("Name") },
        )

        OutlinedTextField(
            value = password,
            onValueChange = viewModel::setPassword,
            label = { Text("Password") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = viewModel::register) {
            Text("Register")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Surface(onClick = { navController.navigate("login") }) {
            Column {
                Text(
                    text = "¿Ya estas registrado?",
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
                    text = "Ingresa",
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
