package ar.edu.unlam.mobile.scaffolding.ui.components
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class RegistrationViewModel : ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _pass = mutableStateOf("")
    val pass: State<String> = _pass

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onPassChange(newPass: String) {
        _pass.value = newPass
    }

    fun onSubmit() {
        TODO("Not yet implemented")
    }
}