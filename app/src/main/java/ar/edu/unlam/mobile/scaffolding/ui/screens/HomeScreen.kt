package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.Loader
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed

@Composable
fun HomeScreen(
    navController: NavHostController? = null,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    // La información que obtenemos desde el view model la consumimos a través de un estado de
    // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
    // un estado de éxito y un mensaje de error.
    val tuitState: TuitUIState by viewModel.feedDataState.collectAsState()

    when (val feedState = tuitState.tuitFeedUIState) {
        is TuitFeedUIState.Loading -> {
            Loader()
        }

        is TuitFeedUIState.Success -> {
            TuitFeed(feedState.tuits, likeEvent = { viewModel.likeButtonPressed(it) })
        }
        is TuitFeedUIState.Error -> {
            Text(text = "Error")
        }
    }
}
