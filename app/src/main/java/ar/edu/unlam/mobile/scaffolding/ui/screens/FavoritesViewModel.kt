package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.favorites.usecase.FavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface FavoritesUIState {
    data class Success(
        val favorites: List<FavoriteUser>,
    ) : FavoritesUIState

    data object Loading : FavoritesUIState

    data class Error(
        val message: String = "Error al traer los datos",
    ) : FavoritesUIState
}

data class FavoritesViewModelState(
    val favoriteList: FavoritesUIState,
)

@HiltViewModel
class FavoritesViewModel
    @Inject
    constructor(
        private val favoritesRepository: FavoritesUseCase,
    ) : ViewModel() {
        private val _favoriteList =
            MutableStateFlow(
                FavoritesViewModelState(
                    MutableStateFlow(FavoritesUIState.Loading).value,
                ),
            )

        val favoriteList = _favoriteList.asStateFlow()

        init {
            viewModelScope.launch {
                favoritesRepository.getFavorites().collect {
                    _favoriteList.value = FavoritesViewModelState(FavoritesUIState.Success(it))
                }
            }
        }

        fun deleteFromFavorites(user: FavoriteUser) {
            viewModelScope.launch {
                favoritesRepository.deleteFavoriteUser(user)
            }
        }
    }
