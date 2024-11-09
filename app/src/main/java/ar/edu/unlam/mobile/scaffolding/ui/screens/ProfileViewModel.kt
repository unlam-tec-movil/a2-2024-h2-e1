package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.GetFeedService
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface ProfilePopulationState {
    data class Success(
        val user: User,
        val tuits: List<Tuit>?,
    ) : ProfilePopulationState

    data object Loading : ProfilePopulationState

    data class Error(
        val message: String,
    ) : ProfilePopulationState
}

data class ProfileUiState(
    val profileState: ProfilePopulationState,
)

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val getUserService: GetUserService,
        private val feedService: GetFeedService,
    ) : ViewModel() {
        private val _fetchUserState = MutableStateFlow(ProfileUiState(MutableStateFlow(ProfilePopulationState.Loading).value))
        val fetchUserState = _fetchUserState.asStateFlow()
    fun likeButtonPressed(id: Int) {
        viewModelScope.launch {
            Log.i("ButtonLike", "Te gusta! el post $id")
        }
    }

        init {
            viewModelScope.launch {
                val user = getUserService.getUserData()
                val tuits = feedService.getFeed(1)


                if (user != null && tuits != null) {
                    _fetchUserState.value = ProfileUiState(ProfilePopulationState.Success(user, tuits))
                } else {
                    _fetchUserState.value = ProfileUiState(ProfilePopulationState.Error("Error al cargar perfil y tuits"))
                }
            }
            }
        }

