package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.PostNewTuitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface PostTuitResultState {
    data object Success : PostTuitResultState

    data object Writing : PostTuitResultState

    data object Loading : PostTuitResultState

    data class Error(
        val message: String,
    ) : PostTuitResultState
}

data class PostUiState(
    val resultState: PostTuitResultState,
)

@HiltViewModel
class
NewTuitViewModel
    @Inject
    constructor(
        private val postNewTuitUseCase: PostNewTuitUseCase,
    ) : ViewModel() {
        private val _message = mutableStateOf("")
        val message: State<String> = _message
        private val _postUIState = MutableStateFlow(PostUiState(MutableStateFlow(PostTuitResultState.Writing).value))
        val postUIState = _postUIState.asStateFlow()

        fun setMessage(message: String) {
            viewModelScope.launch {
                postNewTuitUseCase.addPostToLocalData(message)
            }
            this._message.value = message
        }

        init {
            viewModelScope.launch {
                _message.value = mutableStateOf(postNewTuitUseCase.getLastTuitFromLocalData()).value
            }
        }

        fun storeTuit() {
            viewModelScope.launch {
                postNewTuitUseCase.addPostToLocalData(_message.value)
            }
        }

        fun postTuit() {
            viewModelScope.launch {
                val result = postNewTuitUseCase.postTuit(tuit = _message.value)
                if (result.status == 0) {
                    _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                } else {
                    _postUIState.value = PostUiState(PostTuitResultState.Success)
                }
            }
        }
    }
