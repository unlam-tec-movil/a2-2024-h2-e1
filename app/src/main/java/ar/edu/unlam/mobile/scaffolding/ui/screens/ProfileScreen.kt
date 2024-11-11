package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.ui.components.Loader
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed
import ar.edu.unlam.mobile.scaffolding.ui.components.UserDetails

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    newTuitViewModel: NewTuitViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier,
) {
    val logState: ProfileUiState by viewModel.fetchUserState.collectAsState()
    val textMessage by newTuitViewModel.message

    when (val profileData = logState.profileState) {
        is ProfilePopulationState.Success -> {
            Column (modifier = modifier){
            UserDetails(profileData.user,navController)
                profileData.tuits?.let { tuits ->
                    TuitFeed(
                        tuits = tuits,
                        likeEvent = { tuitId -> viewModel.likeButtonPressed(tuitId) }
                    ) }
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
