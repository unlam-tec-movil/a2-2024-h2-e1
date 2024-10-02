package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface TuitFeedUIState {
    data class Success(
        val tuits: List<Tuit>,
    ) : TuitFeedUIState

    data object Loading : TuitFeedUIState

    data class Error(
        val message: String,
    ) : TuitFeedUIState
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
        // Mutable State Flow contiene un objeto de estado mutable. Simplifica la operación de
        // actualización de información y de manejo de estados de una aplicación: Cargando, Error, Éxito
        // (https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
        // _helloMessage State es el estado del componente "HelloMessage" inicializado como "Cargando"
        private val helloMessage = MutableStateFlow(TuitFeedUIState.Loading)

        // _Ui State es el estado general del view model.
        private val _uiState =
            MutableStateFlow(
                TuitUIState(helloMessage.value),
            )

        // UIState expone el estado anterior como un Flujo de Estado de solo lectura.
        // Esto impide que se pueda modificar el estado desde fuera del ViewModel.
        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                repo
                    .getTuits()
                    .catch {
                        _uiState.value = TuitUIState(TuitFeedUIState.Error("Error al obtener los tuits"))
                    }.collect { tuits ->
                        _uiState.value = TuitUIState(TuitFeedUIState.Success(tuits))
                    }
            }
        }
    }
