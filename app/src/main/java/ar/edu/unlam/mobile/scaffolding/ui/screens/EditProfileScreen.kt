package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.launch
import ar.edu.unlam.mobile.scaffolding.ui.components.ProfileHeader

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val logState by viewModel.loggedState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var user by remember { mutableStateOf<User?>(null) }


    LaunchedEffect(Unit) {
        try {
            user = viewModel.getCurrentUser()
        } catch (e: Exception) {
            // El usuario no está autenticado o hubo un error
        }
    }

    when (logState.loggedUserUiState) {
        is LoggedUserUIState.Logged -> {
            user?.let { currentUser ->
                val name = remember { mutableStateOf(currentUser.name) }
                val email = remember { mutableStateOf(currentUser.email) }

                Column(modifier = Modifier.padding(16.dp)) {
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
            } ?: Text(text = "Error al cargar el usuario")
        }

        LoggedUserUIState.NotLogged -> {
            Text(text = "Usuario no autenticado")
        }

        else -> {
            Text(text = "Cargando...")
        }
    }
}
