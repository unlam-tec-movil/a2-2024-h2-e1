package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.sp
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
                Column {
                    Text(
                        text = "Usuarios seguidos",
                        fontSize = 24.sp,
                        modifier =
                            Modifier
                                .padding(top = 16.dp, start = 16.dp, bottom = 20.dp)
                                .align(Alignment.Start),
                    )
                    LazyColumn(
                        modifier.padding(
                            top = 10.dp,
                            bottom = 80.dp,
                            start = 10.dp,
                            end = 10.dp,
                        ),
                    ) {
                        items(favState.favorites) { fav ->
                            Card {
                                Row(
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(top = 8.dp, start = 16.dp, bottom = 8.dp, end = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
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
                                    Text(text = fav.name, Modifier.padding(horizontal = 10.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
        is FavoritesUIState.Error -> Text(favState.message)
        FavoritesUIState.Loading -> Loader()
    }
}
