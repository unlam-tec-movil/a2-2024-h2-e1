package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.entity.LocalUserEntity
import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.UpdateProfileBodyDto
import ar.edu.unlam.mobile.scaffolding.domain.login.services.UserLoginService
import ar.edu.unlam.mobile.scaffolding.domain.login.services.UserRegistrationService
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.services.GetUserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface LogInUIState {
    data class Success(
        val user: User,
    ) : LogInUIState

    data object Loading : LogInUIState

    data class Error(
        val message: String,
    ) : LogInUIState
}

data class LogUIState(
    val logState: LogInUIState,
)

@Immutable
sealed interface LoggedUserUIState {
    data object Logged : LoggedUserUIState

    data object NotLogged : LoggedUserUIState
}

data class IsLoggedUIState(
    val loggedUserUiState: LoggedUserUIState,
)

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val userLogin: UserLoginService,
    private val registrationService: UserRegistrationService,
    private val getUserService: GetUserService,
    private val localDataRepository: LocalDataRepository,
) : ViewModel() {
    private val _email = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _name = mutableStateOf("")

    val name: State<String> = _name
    val password: State<String> = _password
    val email: State<String> = _email

    private val _loggedState =
        MutableStateFlow(IsLoggedUIState(MutableStateFlow(LoggedUserUIState.NotLogged).value))
    val loggedState = _loggedState.asStateFlow()


    fun setPassword(password: String) {
        this._password.value = password
    }

    fun setEmail(email: String) {
        this._email.value = email
    }

    fun setName(newName: String) {
        this._name.value = newName
    }

    fun logIn() {
        viewModelScope.launch {
            if (userLogin.login(email = _email.value, password = _password.value)) {
                if (getUserService.getUserData() != null) {
                    _loggedState.value = IsLoggedUIState(LoggedUserUIState.Logged)
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            if (registrationService.register(
                    email = _email.value,
                    name = _name.value,
                    password = _password.value,
                )
            ) {
                if (getUserService.getUserData() != null) {
                    _loggedState.value = IsLoggedUIState(LoggedUserUIState.Logged)
                }
            }
        }
    }

    suspend fun getCurrentUser(): User {
        return getUserService.getUserData()?.also { user ->
            _name.value = user.name
            _email.value = user.email
            _loggedState.value = IsLoggedUIState(LoggedUserUIState.Logged)
        } ?: throw IllegalStateException("Usuario no autenticado")
    }

    fun updateProfile(name: String, avatarUrl: String? = null, password: String? = null) {
        viewModelScope.launch {
            try {
                val token = localDataRepository.getLoginToken()

                if (token != null) {
                    val updateProfileBody = UpdateProfileBodyDto(
                        name = name,
                        avatar_url = avatarUrl ?: "",
                        password = password
                    )
                    val updatedUser = userLogin.updateProfile(token,updateProfileBody)

                    updatedUser?.let {
                        _name.value = it.name
                        _email.value = it.email
                    }
                } else {
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error al actualizar perfil: ${e.message}")
            }
        }
    }
}
