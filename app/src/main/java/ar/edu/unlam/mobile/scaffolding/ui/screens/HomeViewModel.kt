package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.pagination.usecases.PaginationManagerInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeedUseCase
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.LikeUseCase
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
        private val feedService: GetFeedUseCase,
        private val likeUseCase: LikeUseCase,
        private val paginationService: PaginationManagerInterface,
    ) : ViewModel() {
        private val _feedDataState = MutableStateFlow(TuitUIState(MutableStateFlow(TuitFeedUIState.Loading).value))
        val feedDataState = _feedDataState.asStateFlow()

        init {
            viewModelScope.launch {
                val tuits = feedService.getFeed()
                if (tuits != null) {
                    _feedDataState.value = TuitUIState(TuitFeedUIState.Success(tuits))
                }
            }
        }

        fun likeButtonPressed(tuit: Tuit) {
            viewModelScope.launch {
                likeUseCase.changeLikeStatus(tuit.liked, tuit.id)
                val tuits = feedService.getFeed()
                if (tuits != null) {
                    _feedDataState.value = TuitUIState(TuitFeedUIState.Success(tuits))
                }
            }
        }
    }
