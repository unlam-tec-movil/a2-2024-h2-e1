package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel
    @Inject
    constructor(
        val repo: UserRepository,
    ) : ViewModel() {
        private val _userIsLogged = mutableStateOf<User?>(null)
        val userIsLogged: State<User?> = _userIsLogged
    }
