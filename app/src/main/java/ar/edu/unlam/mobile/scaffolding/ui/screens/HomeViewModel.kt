package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.network.repository.ApiRepository
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
        private val apiRepository: ApiRepository
    ) : ViewModel() {
        private val _feedDataState = MutableStateFlow(TuitUIState(MutableStateFlow(TuitFeedUIState.Loading).value))
        val feedDataState = _feedDataState.asStateFlow()


    // Método para dar like a un post
    fun likePost(postId: Int) {
        viewModelScope.launch {
            try {
                val response = apiRepository.likePost(postId, "token")
                updateTuitLikeStatus(postId, liked = true)
                /*
                if (response.status == 1) {
                    // Actualiza el estado local del post
                    //val updatedPost = post.value?.copy(liked = true)
                    //_post.value = updatedPost
                    updateTuitLikeStatus(postId, liked = true)
                } else {
                    // Manejar el error
                }

                 */
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
  /*
    fun likePost(id: Int) {
        viewModelScope.launch {
            try {
                val response = apiRepository.likePost(id, "token")
                if (response.status == 1) {
                    updateTuitLikeStatus(id, liked = true)
                } else {
                    Log.e("LikeError", "Error al dar like: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("LikeError", "Exception al dar like: ${e.message}")
            }
        }

   */


    // Método para quitar el like de un post
    fun unlikePost(id: Int) {
        viewModelScope.launch {
            try {
                val response = apiRepository.unlikePost(id, "token")
                if (response.status == 1) {
                    updateTuitLikeStatus(id, liked = false)
                } else {
                    Log.e("UnlikeError", "Error al quitar like: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("UnlikeError", "Exception al quitar like: ${e.message}")
            }
        }
    }

    // Método para actualizar el estado de "liked" en el tuit
    private fun updateTuitLikeStatus(id: Int, liked: Boolean) {
        val currentState = _feedDataState.value
        if (currentState.tuitFeedUIState is TuitFeedUIState.Success) {
            val updatedTuits = (currentState.tuitFeedUIState as TuitFeedUIState.Success).tuits.map { tuit ->
                if (tuit.id == id) {
                    tuit.copy(liked = liked, likes = if (liked) tuit.likes + 1 else tuit.likes - 1)
                } else {
                    tuit
                }
            }
            _feedDataState.value = TuitUIState(TuitFeedUIState.Success(updatedTuits))
        }
    }

        fun likeButtonPressed(id: Int) {
            viewModelScope.launch {
                Log.i("ButtonLike", "Te gusta! el post $id")
            }
        }

        init {
            viewModelScope.launch {
                val tuits = feedService.getFeed(1)
                if (tuits != null) {
                    _feedDataState.value = TuitUIState(TuitFeedUIState.Success(tuits))
                }
            }
        }
    }
