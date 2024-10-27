package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    if (getUserService.getUserData()) {
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
                    if (getUserService.getUserData()) {
                        _loggedState.value = IsLoggedUIState(LoggedUserUIState.Logged)
                    }
                }
            }
        }
    }
