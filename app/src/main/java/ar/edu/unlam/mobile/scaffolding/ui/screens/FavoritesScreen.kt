package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.Loader
import coil.compose.AsyncImage

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val favoriteList by viewModel.favoriteList.collectAsState()
    when (val favState = favoriteList.favoriteList) {
        is FavoritesUIState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier.padding(top = 10.dp)) {
                    // item { TweetComposer() }
                    items(favState.favorites) { fav ->
                        Card {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(fav.name)
                                AsyncImage(
                                    contentDescription = "profile picture",
                                    contentScale = ContentScale.Crop,
                                    model = fav.avatarUrl,
                                    modifier =
                                        Modifier
                                            .clip(CircleShape)
                                            .size(60.dp)
                                            .width(60.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
        is FavoritesUIState.Error -> Text(favState.message)
        FavoritesUIState.Loading -> Loader()
    }
}
