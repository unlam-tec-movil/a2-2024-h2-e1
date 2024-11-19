package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    when (userState.profileState) {
        is ProfilePopulationState.Success -> {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center) {
                Text(text = "Editar perfil",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 20.dp)
                        .align(Alignment.CenterHorizontally) )
                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.setName(it) },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = avatarUrl,
                    onValueChange = { viewModel.setAvatar(it) },
                    label = { Text("URL del Avatar") },
                )

                OutlinedButton(
                    onClick = {
                        viewModel.updateProfile()
                        navController.navigate("profile")
                    },
                    modifier = Modifier.padding(top = 16.dp)
                                       .align(Alignment.CenterHorizontally),
                ) {
                    Text(text = "Guardar cambios")
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
