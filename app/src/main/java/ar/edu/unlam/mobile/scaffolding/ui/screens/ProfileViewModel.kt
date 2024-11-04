package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    ) : ViewModel() {
        private val _fetchUserState = MutableStateFlow(ProfileUiState(MutableStateFlow(ProfilePopulationState.Loading).value))
        val fetchUserState = _fetchUserState.asStateFlow()

        init {
            viewModelScope.launch {
                val user = getUserService.getUserData()
                if (user != null) {
                    _fetchUserState.value = ProfileUiState(ProfilePopulationState.Success(user))
                } else {
                    _fetchUserState.value = ProfileUiState(ProfilePopulationState.Error("error"))
                }
            }
        }
    }
