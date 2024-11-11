package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ar.edu.unlam.mobile.scaffolding.ui.components.ProfileHeader

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val logState by viewModel.loggedState.collectAsState()

    when (logState.loggedUserUiState) {
        is LoggedUserUIState.Logged -> {
            val user = viewModel.getCurrentUser()
            val name = remember { mutableStateOf(user.name) }
            val email = remember { mutableStateOf(user.email) }
            val coroutineScope = rememberCoroutineScope()

            Column(modifier = Modifier.padding(16.dp)) {
                ProfileHeader(user = user,navController)

                TextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Nombre") }
                )

                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Correo Electrónico") }
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.updateUser(name.value, email.value)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Guardar Cambios")
                }
            }
        }

        is LoggedUserUIState.NotLogged -> {
            Text(text = "Usuario no autenticado")
        }

        else -> {
            Text(text = "Cargando...")
        }
    }
}