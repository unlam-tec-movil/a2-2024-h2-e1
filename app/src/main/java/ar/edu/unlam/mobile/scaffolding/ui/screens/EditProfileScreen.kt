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
import ar.edu.unlam.mobile.scaffolding.ui.components.Loader

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val userState: ProfileUiState by viewModel.fetchUserState.collectAsState()

    val name by viewModel.name
    val avatarUrl by viewModel.avatarUrl

    when (val userData = userState.profileState) {
        is ProfilePopulationState.Success -> {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    value = name,
                    onValueChange = { viewModel.setName(it) },
                    label = { Text("Nombre") },
                )

                TextField(
                    value = avatarUrl,
                    onValueChange = { viewModel.setAvatar(it) },
                    label = { Text("URL del Avatar") },
                )

                Button(
                    onClick = { viewModel.updateProfile() },
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    Text(text = "Guardar Cambios")
                }
            }
        }
        is ProfilePopulationState.Loading -> {
            Loader()
        }
        is ProfilePopulationState.Error -> {
            Text(text = "Error")
        }
    }
}
