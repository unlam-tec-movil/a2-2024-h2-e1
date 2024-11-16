package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val logState by viewModel.loggedState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var user by remember { mutableStateOf<User?>(null) }

    var name by remember { mutableStateOf("") }
    var avatarUrl by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            user = viewModel.getCurrentUser()
            user?.let {
                name = it.name
                avatarUrl = it.avatar_url ?: ""
            }
        } catch (e: Exception) {

        }
    }

    when (logState.loggedUserUiState) {
        is LoggedUserUIState.Logged -> {
            user?.let {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") }
                    )

                    TextField(
                        value = avatarUrl,
                        onValueChange = { avatarUrl = it },
                        label = { Text("URL del Avatar") }
                    )

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val updatedUser = viewModel.updateProfile(name, avatarUrl.takeIf { it.isNotEmpty() })
                                if (updatedUser != null) {
                                    user = updatedUser
                                    navController.popBackStack()
                                } else {
                                }
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