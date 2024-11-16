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

    // Variables para el formulario de edición
    var name by remember { mutableStateOf("") }
    var avatarUrl by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            user = viewModel.getCurrentUser()
            user?.let {
                name = it.name
            }
        } catch (e: Exception) {
        }
    }

    when (logState.loggedUserUiState) {
        is LoggedUserUIState.Logged -> {
            user?.let { currentUser ->

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
                                viewModel.updateProfile(name, avatarUrl.takeIf { it.isNotEmpty() })
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