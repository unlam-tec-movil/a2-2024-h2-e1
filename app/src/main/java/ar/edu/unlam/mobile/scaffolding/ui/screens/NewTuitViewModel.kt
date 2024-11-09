package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.services.PostNewTuitService
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

    data class Liked(val postId: Int) : PostTuitResultState
    data class Unliked(val postId: Int) : PostTuitResultState
}

data class PostUiState(
    val resultState: PostTuitResultState,

)

@HiltViewModel
class NewTuitViewModel
    @Inject
    constructor(
        private val postNewTuitService: PostNewTuitService,
        private val apiRepository: ApiRepository
    ) : ViewModel() {
        private val _message = mutableStateOf("")
        val message: State<String> = _message
        private val _postUIState = MutableStateFlow(PostUiState(MutableStateFlow(PostTuitResultState.Writing).value))
        val postUIState = _postUIState.asStateFlow()

        fun setMessage(message: String) {
            viewModelScope.launch {
                postNewTuitService.addPostToLocalData(message)
            }
            this._message.value = message
        }

        init {
            viewModelScope.launch {
                _message.value = mutableStateOf(postNewTuitService.getLastTuitFromLocalData()).value
            }
        }

        fun storeTuit() {
            viewModelScope.launch {
                postNewTuitService.addPostToLocalData(_message.value)
            }
        }

        fun postTuit() {
            viewModelScope.launch {
                val result = postNewTuitService.postTuit(tuit = _message.value)
                if (result.status == 0) {
                    _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                } else {
                    _postUIState.value = PostUiState(PostTuitResultState.Success)
                }
            }
        }


        // Función para "like" un tuit
        fun likePost(postId: Int) {
            viewModelScope.launch {
                try {
                    val result = apiRepository.likePost(postId, "token")
                  //  if (result.status == 0) {
                  //      _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                //    } else {
                        // Actualiza el estado local del Tuit
                        // ... (aquí actualizarías el estado local de tu lista de tuits)
                        _postUIState.value = PostUiState(PostTuitResultState.Liked(postId))
                 //   }
                } catch (e: Exception) {
                    Log.e("Error", e.message.orEmpty())
                    _postUIState.value = PostUiState(PostTuitResultState.Error(e.message.orEmpty()))
                }
            }
        }
    /*
        fun likePost(postId: Int) {
            viewModelScope.launch {
                try {
                    val result = postNewTuitService.likePost(postId) // Lógica de like
                    if (result.status == 0) {
                        _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                    } else {
                        // Actualiza el estado con el postId que se ha dado like
                        _postUIState.value = PostUiState(PostTuitResultState.Liked(postId))
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.message.orEmpty())
                    _postUIState.value = PostUiState(PostTuitResultState.Error(e.message.orEmpty()))
                }
            }
        }

     */

        // Función para "unlike" un tuit
        fun unlikePost(postId: Int) {
            viewModelScope.launch {
                try {
                    val result = apiRepository.unlikePost(postId, "token")
                    if (result.status == 0) {
                        _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                    } else {
                        // Actualiza el estado local del Tuit
                        // ... (aquí actualizarías el estado local de tu lista de tuits)
                        _postUIState.value = PostUiState(PostTuitResultState.Unliked(postId))
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.message.orEmpty())
                    _postUIState.value = PostUiState(PostTuitResultState.Error(e.message.orEmpty()))
                }
            }
        }
/*
        fun unlikePost(postId: Int) {
            viewModelScope.launch {
                try {
                    val result = postNewTuitService.unlikePost(postId) // Lógica de unlike
                    if (result.status == 0) {
                        _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                    } else {
                        // Actualiza el estado con el postId que se ha dado unlike
                        _postUIState.value = PostUiState(PostTuitResultState.Unliked(postId))
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.message.orEmpty())
                    _postUIState.value = PostUiState(PostTuitResultState.Error(e.message.orEmpty()))
                }
            }
        }

 */




    }
