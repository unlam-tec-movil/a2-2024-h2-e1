package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage
import ar.edu.unlam.mobile.scaffolding.domain.tuit.usecases.DraftUseCase
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

sealed interface GetDraftTuitsState {
    data class Success(
        val drafts: List<SavedMessage>,
    ) : GetDraftTuitsState

    data object Loading : GetDraftTuitsState
}

data class DraftsUIState(
    val draftList: GetDraftTuitsState,
)

@HiltViewModel
class
NewTuitViewModel
    @Inject
    constructor(
        private val postNewTuitService: PostNewTuitUseCase,
        private val draftRepository: DraftUseCase,
    ) : ViewModel() {
        private val _message = mutableStateOf("")
        val message: State<String> = _message

        private val _draftDataState = MutableStateFlow(DraftsUIState(MutableStateFlow(GetDraftTuitsState.Loading).value))

        private val _postUIState = MutableStateFlow(PostUiState(MutableStateFlow(PostTuitResultState.Writing).value))

        val postUIState = _postUIState.asStateFlow()
        val draftDataState = _draftDataState.asStateFlow()

        fun setMessage(message: String) {
            viewModelScope.launch {
                postNewTuitService.addPostToLocalData(message)
            }
            this._message.value = message
        }

        init {
            viewModelScope.launch {
                _message.value = mutableStateOf(postNewTuitService.getLastTuitFromLocalData()).value
                draftRepository.getDraftFromDatabase().collect {
                    _draftDataState.value = DraftsUIState(GetDraftTuitsState.Success(it))
                }
                Log.i("VMTAG", "Draft data: ${_draftDataState.value}")
            }
        }

        fun storeTuit() {
            viewModelScope.launch {
                draftRepository.addToDabase(_message.value)
                draftRepository.getDraftFromDatabase().collect {
                    _draftDataState.value = DraftsUIState(GetDraftTuitsState.Success(it))
                }
            }
        }

        fun selectMessage(draft: SavedMessage) {
            this._message.value = draft.text
        }

        fun deleteMessage(draft: SavedMessage) {
            viewModelScope.launch {
                draftRepository.deleteFromDatabase(draft)
                draftRepository.getDraftFromDatabase().collect {
                    _draftDataState.value = DraftsUIState(GetDraftTuitsState.Success(it))
                }
            }
        }

        fun postTuit() {
            viewModelScope.launch {
                val result = postNewTuitService.postTuit(tuit = _message.value)
                if (result.status == 0) {
                    _postUIState.value = PostUiState(PostTuitResultState.Error(result.message))
                } else {
                    _postUIState.value = PostUiState(PostTuitResultState.Success)
                    _message.value = mutableStateOf(postNewTuitService.getLastTuitFromLocalData()).value
                }
            }
        }
    }
