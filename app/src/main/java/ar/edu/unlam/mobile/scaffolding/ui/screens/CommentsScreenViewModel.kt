package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
sealed interface TuitComments {
    data class Success(
        val comentarios: List<Comment>,
    ) : TuitComments

    data object Loading : TuitComments

    data class Error(
        val message: String,
    ) : TuitComments
}

data class TuitCommentsState(
    val TuitComments: TuitComments,
)

@HiltViewModel
class CommentsScreenViewModel
    @Inject
    constructor() : ViewModel()
