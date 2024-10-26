package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface TuitFeedUIState {
    data class Success(
        val tuits: List<Tuit>,
        ) : TuitFeedUIState

    data object Loading : TuitFeedUIState

    data class Error(val message: String) : TuitFeedUIState
}

data class TuitUIState(
    val tuitFeedUIState: TuitFeedUIState,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        repo: TuitRepository,
    ) : ViewModel() {

        private val helloMessage = MutableStateFlow(TuitFeedUIState.Loading)

        private val _uiState =
            MutableStateFlow(
                TuitUIState(helloMessage.value),
            )

        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                repo.getTuits().collect{ tuits ->
                _uiState.value = TuitUIState(TuitFeedUIState.Success(tuits))
                }
            }
        }
    }
