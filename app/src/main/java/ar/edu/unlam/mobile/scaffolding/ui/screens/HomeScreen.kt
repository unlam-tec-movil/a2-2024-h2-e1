package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitCard
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState: TuitUIState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        when (val tuitState = uiState.tuitFeedUIState) {
            is TuitFeedUIState.Loading -> {
                // Loading
                CircularProgressIndicator()
            }

            is TuitFeedUIState.Success -> {
                // Success
                TuitFeed(tuits = tuitState.tuits)
            }

            is TuitFeedUIState.Error -> {
                // Error
            }
        }

        Button(
            onClick = { navController.navigate("new_tuit")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Nuevo Tuit")
        }
    }
}
