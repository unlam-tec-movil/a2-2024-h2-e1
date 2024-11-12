package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.pagination.service.PaginationManagerInterface
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
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
        private val feedService: GetFeedService,
        private val paginationService: PaginationManagerInterface,
    ) : ViewModel() {
        private val _feedDataState = MutableStateFlow(TuitUIState(MutableStateFlow(TuitFeedUIState.Loading).value))
        val feedDataState = _feedDataState.asStateFlow()

        fun likeButtonPressed(id: Int) {
            viewModelScope.launch {
                Log.i("ButtonLike", "Te gusta! el post $id")
            }
        }

        init {
            viewModelScope.launch {
                paginationService.initSaveData()
                val tuits = feedService.getFeed()
                if (tuits != null) {
                    _feedDataState.value = TuitUIState(TuitFeedUIState.Success(tuits))
                }
            }
        }
    }
