package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.GetFeedUseCase
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.usecases.GetUserDataUseCase
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
        private val getUserDataUseCase: GetUserDataUseCase,
        private val feedService: GetFeedUseCase,
    ) : ViewModel() {
        private val _name = mutableStateOf("")
        private val _avatarUrl = mutableStateOf("")

        val name: State<String> = _name
        val avatarUrl: State<String> = _avatarUrl

        private val _fetchUserState =
            MutableStateFlow(ProfileUiState(MutableStateFlow(ProfilePopulationState.Loading).value))
        val fetchUserState = _fetchUserState.asStateFlow()

        init {
            viewModelScope.launch {
                getCurrentUser()
            }
        }

        private suspend fun getCurrentUser() {
            val user = getUserDataUseCase.getUserData()
            feedService.getFeed()

            if (user != null) {
                // Obtener los tuits filtrados por el correo del usuario
                val tuits = feedService.getUserTuitsByEmail(user.name)
                _name.value = user.name
                _avatarUrl.value = user.avatar_url

                if (tuits != null) {
                    _fetchUserState.value = ProfileUiState(ProfilePopulationState.Success(user, tuits))
                } else {
                    _fetchUserState.value =
                        ProfileUiState(ProfilePopulationState.Error("Error al cargar tuits"))
                }
            } else {
                _fetchUserState.value =
                    ProfileUiState(ProfilePopulationState.Error("Error al cargar perfil"))
            }
        }

        fun updateProfile() {
            viewModelScope.launch {
                try {
                    val user = getUserDataUseCase.updateProfile(_name.value, _avatarUrl.value)
                    getCurrentUser()
                } catch (e: Exception) {
                    Log.e("ProfileViewModel", "Error al actualizar el perfil")
                }
            }
        }

        fun setAvatar(it: String) {
            _avatarUrl.value = it
        }

        fun setName(it: String) {
            _name.value = it
        }
    }
