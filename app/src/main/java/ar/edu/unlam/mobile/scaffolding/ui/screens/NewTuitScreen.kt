package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.ui.components.DraftList
import ar.edu.unlam.mobile.scaffolding.ui.components.Loader
import ar.edu.unlam.mobile.scaffolding.ui.components.NewTuit

@Composable
fun NewTuitScreen(
    viewModel: NewTuitViewModel = hiltViewModel(),
    navController: NavController,
) {
    val newTuitState: PostUiState by viewModel.postUIState.collectAsState()
    val draftsState: DraftsUIState by viewModel.draftDataState.collectAsState()
    Column {
        when (val profileData = newTuitState.resultState) {
            is PostTuitResultState.Success -> {
                navController.navigate("home")
            }

            is PostTuitResultState.Loading -> {
                Loader()
            }

            is PostTuitResultState.Error -> {
                Text(text = "Error")
            }
            else -> {
                val message by viewModel.message
                Column {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Filled.Close, contentDescription = "Cerrar")
                    }
                    NewTuit(
                        addTuit = { viewModel.postTuit() },
                        setNewMessage = { viewModel.setMessage(it) },
                        storeTuit = { viewModel.storeTuit() },
                        textMessage = message,
                        goHome = { navController.navigate("home") },
                    )
                }
            }
        }
        when (val draft = draftsState.draftList) {
            is GetDraftTuitsState.Loading -> {
                Loader()
            }

            is GetDraftTuitsState.Success -> {
                DraftList(
                    drafts = draft.drafts,
                    clickDraft = { viewModel.selectMessage(it) },
                    deleteDraft = { viewModel.deleteMessage(it) },
                )
            }
        }
    }
}
